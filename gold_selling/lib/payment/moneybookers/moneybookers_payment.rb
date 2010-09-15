class MoneybookersPayment
  
  @@root=File.expand_path(File.dirname(__FILE__))
  @@config=YAML.load_file(File.join(@@root, 'moneybookers.yml'))
  @@headers={"content-Type"=>"application/x-www-form-urlencoded"}
  @@url = "www.moneybookers.com"
  def dopay(purchase_attemp,request)
    self.prepare(purchase_attemp,request)
  end
  
  def prepare(purchase_attemp,request)
    host=request.host.to_s
    port=request.port.to_s
    @request={  :pay_to_email=>@@config["EMAIL"],
                :recipient_description=>"www.instantwowgoldmine.com",
                :transaction_id => purchase_attemp.gh_transaction_id,
                :language=>'EN',
                :currency =>purchase_attemp.concurrency.upcase,
                :detail1_description=>"Product ID:",
                :detail1_text=>purchase_attemp.pk,
                :prepare_only=>'1',
                :amount => sprintf("%.#{2}f",purchase_attemp.price),
                :return_url_text=> "gold_selling",
                :return_url_target=>"_self",
                :cancel_url=>"http://#{host}:#{port}/contact-us.html",
                :cancel_url_target=> '_self',
                :return_url=>"http://#{host}:#{port}/moneybookers_success",
                :status_url=>"http://#{host}:#{port}/moneybookers_status_url",
                :confirmation_note=>'hi confirmation'}
    
    req_data=Hash2urlHelper.hash2cgiString(@request)
    http=Net::HTTP.new(@@url,443)
    http.use_ssl=(true)
    response = http.post2('/app/payment.pl',req_data,@@headers)
    MoneybookersTranaction.new(response)
  end
  
  def tranction_success_url(*response)
    "https://www.moneybookers.com/app/payment.pl?sid=#{response[1]}"  
  end
  
  
  
end