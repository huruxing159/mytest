class MoneybookersTranaction
  def initialize(response)
    sessionid = CGI::parse(response.header["set-cookie"])["SESSION_ID"]
    @success = !(sessionid.nil?)
    @response = sessionid[0]
  end
  
  def success?
    @success
  end
  
  def response
    @response
  end
end