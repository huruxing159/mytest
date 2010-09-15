require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

describe ServerHelper do
  
  it "tell the server_gold_amount" do
    ServerHelper.gold_amount("Aegwynn - US","Alliance").should == 2000000000
    
  end
  
   it "should do something" do
   
    ServerHelper.game_server("Aegwynn - US","Alliance")[:gold_amount].should ==2000000000
    ServerHelper.game_server("Aegwynn - US","Alliance")[:history_unit_price].should ==0.00011
   end
  
end
 