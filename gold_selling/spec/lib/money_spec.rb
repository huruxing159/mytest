require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

describe Money do
  
  it "equals" do
    12.rmb.should == 12.rmb
  end
  
  it "should understand which Money is bigger" do
    12.rmb > 8.rmb
    12.rmb < 9.gbp
  end

  it "should show correct information" do
    25.usd.show.should == '$25.00'
    25.5.usd.show.should == '$25.50'
    0.003.usd.show(4).should == '$0.0030'
  end
  

end