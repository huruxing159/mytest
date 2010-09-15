class PaymentMethodFactory
  
  def PaymentMethodFactory.decide_method(purchase_attemp)
    case purchase_attemp.payment_method
    when "Paypal"
      PaypalPayment
    when "CreditCard"
      CreditCardPayment
    when "Moneybookers"
      MoneybookersPayment.new
    else
      raise "#{purchase_attemp.payment_method} is not support payment method"
    end
  end
  
  def PaymentMethodFactory.decide_record(payment_method)
     case payment_method
      when "Paypal"
        PaypalRecord
      when "CreditCard"
        PaypalRecord
      when "Moneybookers"
        MoneybookersRecord
      else
        raise "#{payment_method} is not support payment record"
      end
  end
  
end