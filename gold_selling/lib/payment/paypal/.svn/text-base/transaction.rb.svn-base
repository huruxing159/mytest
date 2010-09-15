class Transaction
  def initialize(data)
    @success = data["ACK"].to_s!='Failure'
    @response=data
  end
  
  def success?
    @success
  end
  
  def response
    @response
  end
end