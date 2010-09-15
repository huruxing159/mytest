class PriceStrategyFactory
  
  def PriceStrategyFactory.price_strategy(price_strategy,*rest)
    case price_strategy 
    when "LinearPrice"
      LinearPrice.new(rest[0])
    else
      raise "#{price_strategy} not support now !"
    end
  end
  
end