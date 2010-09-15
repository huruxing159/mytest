class CreditCardPayment 

  def CreditCardPayment.dopay(purchase_attemp,request)
    CreditCardPayment.set_express_checkout(purchase_attemp,request)  
  end
  
  def CreditCardPayment.set_express_checkout(purchase_attemp,request)
    host=request.host.to_s
    port=request.port.to_s
    transaction = Caller.new.call({:METHOD                 	                 => 'SetExpressCheckout',
                                   :PAYMENTREQUEST_0_AMT			               => Money.format_show_amount(purchase_attemp.price,2),
                                   :PAYMENTREQUEST_0_PAYMENTACTION           => 'Sale',
                                   :cancelurl              	                 => "http://#{host}:#{port}/contact-us.html",
                                   :returnurl              		               => "http://#{host}:#{port}/paypal_pay",
                                   :PAYMENTREQUEST_0_CURRENCYCODE            => purchase_attemp.concurrency.upcase,
                                   :PAYMENTREQUEST_0_DESC                    =>'wow',
                                   :L_PAYMENTREQUEST_0_NAME0 				         => purchase_attemp.purchase_info,
                                   :L_PAYMENTREQUEST_0_QTY0 				         => '1',
                                   :L_PAYMENTREQUEST_0_AMT0 				         => Money.format_show_amount(purchase_attemp.price,2),
                                   :L_PAYMENTREQUEST_0_DESC0                 =>"",
                                   :L_PAYMENTREQUEST_0_NUMBER0              => purchase_attemp.gh_transaction_id,
                                   # :EMAIL                                   => purchase_attemp.email,
                                   :SOLUTIONTYPE => 'Sole',
                                   :LANDINGPAGE => 'Billing',
                                   :USER  =>  Profile.credentials["USER"],
                                   :PWD   => Profile.credentials["PWD"],
                                   :SIGNATURE => Profile.credentials["SIGNATURE"],
                                   :SUBJECT => ''  
                                   })
  end
  
  def CreditCardPayment.get_express_checkout_details(token)
     PaypalPayment.get_express_checkout_details(token)
  end
  
  def CreditCardPayment.tranction_success_url(purchase_attemp_id,response)
    PaypalPayment.tranction_success_url(purchase_attemp_id,response)
  end 
  
end