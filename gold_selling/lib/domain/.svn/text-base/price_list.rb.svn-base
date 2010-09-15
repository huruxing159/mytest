class PriceList
  
  AMOUNTS = [1000, 1500, 2000, 2500, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000, 15000, 20000, 30000, 40000, 50000]
  
  def initialize(gold_unit_price, gold_amount,old_price_list = nil, price_strategy = LinearPrice.new(gold_unit_price) )
    puts gold_unit_price.show(8)
      @gold_unit_price ,@gold_amount,@old_price_list, @price_strategy= gold_unit_price,gold_amount,old_price_list, price_strategy
  end
   
  def offers
    old_prices = @old_price_list ? @old_price_list.offers.collect { |o| o.price } : [] 
    amount_list=[]
    AMOUNTS.each {|e| amount_list<<e   if e <=@gold_amount }
    amount_list.zip(old_prices).collect {  |a, old_price|  Offer.new(a, @price_strategy.value(a), old_price) }
  end
  
end
