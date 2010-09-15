class ValidateHelper
  
  def ValidateHelper.string_validate(args,message)
    raise message  if args.to_s.empty? 
  end
  
  def ValidateHelper.email_validate(args)
    ValidateHelper.string_validate(args,"input a email")
    raise "Email is wrong" unless args=~/^([^@\s]+)@((?:[-a-z0-9]+\.)+[a-z]{2,})$/i
  end
  
  def ValidateHelper.number_validate(args,message)
    Float(args) 
    rescue 
      raise message
  end
  
end