class PaypalPayment
  
  def  PaypalPayment.dopay(purchase_attemp,request)
    PaypalPayment.set_express_checkout(purchase_attemp,request)
  end
  
  def PaypalPayment.set_express_checkout(purchase_attemp,request)
    host=request.host.to_s
    port=request.port.to_s
    transaction = Caller.new.call({  :METHOD                 	                 => 'SetExpressCheckout',
                                     :PAYMENTREQUEST_0_AMT			               => Money.format_show_amount(purchase_attemp.price,2),
                                     :PAYMENTREQUEST_0_PAYMENTACTION           => 'Sale',
                                     :cancelurl              	                 => "http://#{host}:#{port}/",
                                     :returnurl              		               => "http://#{host}:#{port}/paypal_pay",
                                     :PAYMENTREQUEST_0_CURRENCYCODE            => purchase_attemp.concurrency.upcase,
                                     :PAYMENTREQUEST_0_DESC                    =>'wow',
                                     :L_PAYMENTREQUEST_0_NAME0 				         => purchase_attemp.purchase_info,
                                     :L_PAYMENTREQUEST_0_QTY0 				         => '1',
                                     :L_PAYMENTREQUEST_0_AMT0 				         => Money.format_show_amount(purchase_attemp.price,2),
                                     :L_PAYMENTREQUEST_0_DESC0                 =>"",
                                     :L_PAYMENTREQUEST_0_NUMBER0              => purchase_attemp.gh_transaction_id,
                                     # :EMAIL                                   => purchase_attemp.email,
                                     :USER  =>  Profile.credentials["USER"],
                                     :PWD   => Profile.credentials["PWD"],
                                     :SIGNATURE => Profile.credentials["SIGNATURE"],
                                     :LANDINGPAGE => 'Login',
                                     :SUBJECT => '' })
  end
  
  def PaypalPayment.get_express_checkout_details(token)
     transaction = Caller.new.call({ :method => 'GetExpressCheckoutDetails',
                                     :token  => token,
                                     :USER  =>  Profile.credentials["USER"],
                                     :PWD   => Profile.credentials["PWD"],
                                     :SIGNATURE => Profile.credentials["SIGNATURE"],
                                     :SUBJECT => '' })
  end
  
  def PaypalPayment.do_express_checkout_payment(order_details)
    transaction = Caller.new.call({   :method        => 'DOExpressCheckoutPayment',
                                      :token         => order_details["TOKEN"].to_s,
                                      :payerid       => order_details["PAYERID"].to_s,
                                      :amt           => order_details["PAYMENTREQUEST_0_AMT"].to_s,
                                      :currencycode  => order_details["PAYMENTREQUEST_0_CURRENCYCODE"],
                                      :paymentaction => 'sale',
                                      :USER  =>  Profile.credentials["USER"],
                                      :PWD   => Profile.credentials["PWD"],
                                      :SIGNATURE => Profile.credentials["SIGNATURE"],
                                      :SUBJECT => ''})
  end
  
  def PaypalPayment.tranction_success_url(purchase_attemp_id,response)
    token = response["TOKEN"].to_s
    PurchaseAttemp.add_token(purchase_attemp_id,token)
    Profile.PAYPAL_URL+token
  end

  
end