class PurchaseAttemp  <Sequel::Model

  @@root=File.expand_path(File.dirname(__FILE__))
  @@config=YAML.load_file(File.join(@@root,'..','payment','moneybookers', 'moneybookers.yml'))
  def PurchaseAttemp.get_a_instance(request)
    params=request.params
    PurchaseAttemp.field_validate(params)
    purchase_attemp=PurchaseAttemp.create(
                                          :region=>params["region"],
                                          :faction=>params["faction"],
                                          :server=>params["server"],
                                          :character=>CGI.escapeHTML(params["character"]),
                                          :payment_method=>params["payment_method"],
                                          :trade_method =>params["trade_method"],
                                          :gold_amount =>params["gold_amount"],
                                          :price =>params["price"].amount,
                                          :unit_price=>params["current_unit_price"].amount,
                                          :concurrency=>CGI.escapeHTML(params["concurrency"]),
                                          :auction_item_name=>CGI.escapeHTML(params["auction_item_name"]) ,
                                          :create_time=>Time.now,
                                          :payment_method_id=>PaymentMethodFactory.decide_record(params["payment_method"]).create(:create_time=>Time.new).pk
                                          )
                                          
    purchase_attemp.update(:gh_transaction_id=>"GH#{Time.now.to_i}P#{purchase_attemp.pk}") 
    purchase_attemp                 
  end
  
  def payment
    PaymentMethodFactory.decide_record(self.payment_method)[self.payment_method_id]
  end

  def PurchaseAttemp.field_validate(params)
     ValidateHelper.string_validate(params["region"],"choose  region")
     ValidateHelper.string_validate(params["faction"],"choose  faction")
     ValidateHelper.string_validate(params["server"],"select a server")
     ValidateHelper.string_validate(params["character"],"input your wow character name")
     ValidateHelper.string_validate(params["payment_method"],"choose payment")
     ValidateHelper.string_validate(params["trade_method"],"select trade_method 'In-game mail ' or 'Face to face trade '")
     ValidateHelper.number_validate(params["gold_amount"],"select  gold amount")
  end

  def purchase_info
    "Gold :#{gold_amount.to_i} costs #{Money.format_show_amount(price,2)}"
  end
  
  def PurchaseAttemp.add_token(gh_transaction_id,token)
     PurchaseAttemp.update(gh_transaction_id,:paypal_token,token) do
         $log.error("paypal token cannot be saved  token=#{token};id=#{id}")
    end
  end
  
  def PurchaseAttemp.update(gh_transaction_id,property_name,value)
    begin
        one_item=PurchaseAttemp.find :gh_transaction_id=>gh_transaction_id
        paypal_record=PaymentMethodFactory.decide_record(one_item[:payment_method])[one_item[:payment_method_id]]
        paypal_record.update(property_name=>value)
      rescue
        yield
      end
  end
  
  def PurchaseAttemp.add_payer_id(gh_transaction_id,payer_id)
    begin
      PurchaseAttemp.update(gh_transaction_id,:paypal_payer_id,payer_id) do
        $log.error(" payer_id cannot be saved  payer_id=#{payer_id}; id=#{id}")
      end
      purchase_attemp=PurchaseAttemp.find :gh_transaction_id=>gh_transaction_id
      order=Order.create_new_order(purchase_attemp)
      InformBackoffice.create_information(order)
    rescue
      $log.error("customer pays success but  a error when save (paypal)")
    end
  end
  
  def PurchaseAttemp.add_transaction_id(gh_transaction_id,transaction_id)
    PurchaseAttemp.update(gh_transaction_id,:paypal_transaction_id,transaction_id) do
      $log.error("transaction is successful but  transaction_id cannot be saved  transaction=#{transaction_id};    id=#{id}")
    end
  end
  
  def PurchaseAttemp.add_moneybookers_record(params)
    raise "no transaction id is returned"   unless params["transaction_id"]
    purchase_attemp=PurchaseAttemp.find :gh_transaction_id=>params["transaction_id"]
    if purchase_attemp.nil? then 
      $log.error("moneybookers callback  but  purchase_attemp  is not found  and transaction_id= #{params['transaction_id']}")
      return 
    end
    payment=purchase_attemp.payment
    params.each{|key,value|  payment.send("#{key}=".to_sym,value.to_s)   if payment.respond_to?(key)}
    md5init=params["merchant_id"].to_s+params["transaction_id"].to_s+Md5Helper.md5(@@config["SECRET"]).upcase+params["mb_amount"].to_s+params["mb_currency"].to_s+params["status"].to_s
    md5= Md5Helper.md5(md5init)
    if (params["md5sig"].upcase == md5.upcase)
      if(Order[:purchase_attemp_id=>purchase_attemp.id].nil?)
        begin
          order=Order.create_new_order(purchase_attemp)
          InformBackoffice.create_information(order)
          payment.save
        rescue
          $log.error("customer pays success but  a error when save  ,purchase_attemp id="+purchase_attemp.id)
        end
      end
    else
      $log.error("md5 verify params= #{params["md5sig"].upcase} ,md5=#{md5}. and params are "+Hash2urlHelper.hash2cgiString(params))
    end 
  end

end