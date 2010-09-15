require File.expand_path(File.dirname(__FILE__) + '/../../spec_helper')


describe ValidateHelper do
  it "raise exception  empty or nil string" do
    lambda{ ValidateHelper.string_validate(nil,"empty")}.should raise_error(RuntimeError)
    lambda{ ValidateHelper.string_validate("","empty")}.should raise_error
  end
  it "pass when string is not nil or  not empty  " do
    lambda{ ValidateHelper.string_validate("something","empty")}.should_not raise_error
  end
  
  it "raise exception  when input is not a email" do
    lambda{ ValidateHelper.email_validate("wwww.wwe.com")}.should raise_error(RuntimeError)
  end
  it "pass when input is a email " do
    lambda{ ValidateHelper.email_validate("huruxing159@sina.com")}.should_not raise_error
    
  end
  
  it "raise  exception when input is not a numeric" do
    lambda{ ValidateHelper.number_validate("33e","should be a number")}.should raise_error
  end
  it "should pass when input is a numeric" do
    lambda{ ValidateHelper.number_validate("55.4","should be a number")}.should_not raise_error
  end
  
  it "should do something" do
    lambda{ValidateHelper.number_validate("500","should bd a float")}.should_not raise_error
  end
end