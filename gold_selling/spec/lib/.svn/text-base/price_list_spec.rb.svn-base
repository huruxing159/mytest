require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

describe PriceList do
  it "should generate offers" do
    offers = PriceList.new(0.004.rmb).offers
    offers.length.should == 17
    offers.first.amount.should == 1000
    offers[6].amount.should == 5000
    offers[6].price.should == 20.rmb
    offers[6].price_per_gold.should == 0.004.rmb
    
  end
  
  it "should add  old price in offers" do
    old_list = PriceList.new(0.003.rmb)
    offers =PriceList.new(0.004.rmb, old_list).offers
    offers[6].old_price.should == 15.rmb
  end

  it "should do discounts" do
     offers =PriceList.new(0.004.rmb,nil,DiscountPrice.new(1000,4.rmb, 0.1)).offers
     offers.first.price.should == 4.rmb
     offer2000 = offers.find { |o| o.amount == 2000 }
     offer2000.price.should == 7.20.rmb
  end
end

