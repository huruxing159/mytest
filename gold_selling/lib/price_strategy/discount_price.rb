class DiscountPrice
  
  LOG2 = Math.log(2)
    
  def initialize(amount,price,discount_fraction)
    @amount,@price,@discount_fraction= amount.to_f,price,discount_fraction
  end
  
  def value(amount)
    ratio = amount/@amount
    @price * (ratio*(1-@discount_fraction)**log2(ratio))
  end
  
  private
  
  def log2(x)
    Math.log(x)/LOG2
  end
  
end