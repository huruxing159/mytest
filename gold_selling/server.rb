require 'rubygems'
require 'sinatra'
require 'haml'
require 'sass'
require 'pathname'
require 'sequel'
require 'sequel_core'
require 'sequel/extensions/inflector'
require 'sequel/extensions/migration'
require 'logger'
require 'net/https'
require 'uri'
require 'cgi'
require 'date'
require 'yaml'

root = File.expand_path(File.dirname(__FILE__))


$log=Logger.new(File.join(root, 'log', "app_#{Date.today}.log"))
$log.level=Logger::WARN

set :app_file, __FILE__
set :param_logging, true    # Set this parameter to true to print parameter trace on each submission

enable :sessions

configure do
  connection_string="postgres://gold_selling:gold_selling@localhost/gold_selling"
  logger = Logger.new(File.join(root, 'log', "db#{Date.today}.log"))
  DB = Sequel.connect(connection_string, :loggers => [logger]) unless Object.const_defined?('DB')
end
Dir.glob(File.join(root,'lib','**','*.rb')).each{|rb|  require rb}

get '/' do
  haml :indexnew
end

post '/serverList' do
  countryName = params["contryName"].to_s
  factionName=params["factionName"].to_s
  return 'no server' if (countryName.nil?  || factionName.nil?)
  countryId=DB[:countries][:name=>countryName][:id]
  return 'no server' if countryId.nil? 
  s=DB[:servers].filter(:country_id=>countryId,:faction=>factionName).order(:id)
  s1=s.map(:name).collect {  |e| f="\"#{e}\","}
end

post '/pricelist' do
  return "choose a server" if params["serverName"].nil?||params["serverName"].empty? 
  return "choose faction" if params["factionName"].nil?||params["factionName"].empty? 
  return "$? or £? or €?" if  params["moneycurrency"].nil?||params["moneycurrency"].empty? 
  gameserver=ServerHelper.game_server(params["serverName"],params["factionName"])
  return 'Server no found ,make sure Server name is right' if gameserver.nil?
  moneycurrency=params["moneycurrency"]
  goldAmount=sprintf("%d", ServerHelper.gold_amount(params["serverName"],params["factionName"]))
  return "The gold in this server is not enough !!!!!!!!!!!!!" if goldAmount.to_i<1000
  gold_amount_format=NumberFormater.format(goldAmount.to_s)
  concurrency=Currency.get_currency_by_name(moneycurrency)
  current_unit_price=gameserver[:current_unit_price].usd.in(concurrency)
  history_unit_price=gameserver[:history_unit_price] ? gameserver[:history_unit_price].usd.in(concurrency) : 0.usd.in(concurrency)  
  session[:current_unit_price]=current_unit_price
  session[:current_price_strategy]=current_price_strategy=PriceStrategyFactory.price_strategy(gameserver[:current_price_strategy],current_unit_price)
  session[:history_price_strategy]=history_price_strategy=PriceStrategyFactory.price_strategy(gameserver[:history_price_strategy],history_unit_price)
  history_price_list=PriceList.new(history_unit_price,goldAmount.to_i,nil,history_price_strategy)
  price_list= PriceList.new(current_unit_price, goldAmount.to_i,history_price_list,current_price_strategy)
  haml :pricelist, :locals => {:serverGoldAmount => gold_amount_format,:price_list =>price_list}
end

post "/calculate_price_custom_amount" do
  serverName=params["serverName"]
  factionName=params["factionName"]
  customAmount=params["custom_gold_amount"];
  moneycurrency=params["moneycurrency"]
  return "$? or £? or €?" if  moneycurrency.nil?||moneycurrency.empty? 
  return "choose a server !!!"  if serverName.nil? ||serverName.empty? 
  return "input amount" if customAmount.nil? ||customAmount.empty?
  return  "input number " unless customAmount=~ /^[\d]+(\.[\d]+){0,1}$/

  gold_unit_price=ServerHelper.gold_unit_price(serverName,factionName).usd
  gold_unit_price=gold_unit_price.in(Currency.get_currency_by_name(moneycurrency))
  price=gold_unit_price*customAmount.to_f
  price.show();
end

post '/buy' do
  redirect '/' if(session[:current_price_strategy].nil?)
  redirect '/' if(session[:current_unit_price].nil?)
  current_price_strategy=session[:current_price_strategy]
  amount=params[:custom_gold_amount].empty? ?params[:goldamount] : params[:custom_gold_amount]
  request.params["gold_amount"]=amount
  request.params["current_unit_price"]=session[:current_unit_price]
  request.params["price"]=current_price_strategy.value(amount.to_i)
  purchase_attemp=PurchaseAttemp.get_a_instance(request)
  payment_method=PaymentMethodFactory.decide_method(purchase_attemp)
  begin
    transaction=payment_method.dopay(purchase_attemp,request)
  rescue SocketError
    $log.error("purchase_attemp.gh_transaction_id=#{purchase_attemp.gh_transaction_id}  SocketError")
    return "something wrong on server"
  end
  if transaction.success? 
      redirect (payment_method.tranction_success_url(purchase_attemp.gh_transaction_id,transaction.response))
  else
     $log.error("purchase_attemp.gh_transaction_id=#{purchase_attemp.gh_transaction_id} "+transaction.response.inspect)
     transaction.response.inspect
  end
  
end

get '/paypal_pay' do
  transaction =PaypalPayment.get_express_checkout_details(params[:token].to_s)
    if transaction.success?  
      order_details=transaction.response
      PurchaseAttemp.add_payer_id(order_details["L_NUMBER0"].to_s,order_details["PAYERID"].to_s)
      result=PaypalPayment.do_express_checkout_payment(order_details)
      if result.success?  
        PurchaseAttemp.add_transaction_id(order_details["L_NUMBER0"].to_s,result.response["PAYMENTINFO_0_TRANSACTIONID"])
        one_item=PurchaseAttemp.find :gh_transaction_id=>order_details["L_NUMBER0"].to_s
        haml :thanks,:locals=>{:purchase_attemp=>one_item}
      else
        return "A successful transaction has already been completed" if  result.response["L_ERRORCODE0"].to_s=="10415"
        $log.error("purchase_attemp pay faild id=#{order_details["L_NUMBER0"].to_s}"+result.response.inspect)
        "pay error"
      end
    else
      transaction.response.inspect
    end
end

get "/moneybookers_success" do
  one_item=PurchaseAttemp.find :gh_transaction_id=>params[:transaction_id]
  haml :thanks,:locals=>{:purchase_attemp=>one_item}
end

begin
post '/moneybookers_status_url' do
  PurchaseAttemp.add_moneybookers_record(params)
end
rescue
end

get "/why_trust_us" do
  haml :why_trust_us
end

get "/contact_us" do
  haml :contact_us
end
