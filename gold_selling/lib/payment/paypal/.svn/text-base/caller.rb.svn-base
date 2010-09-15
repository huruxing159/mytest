require File.expand_path(File.join(File.dirname(__FILE__), 'profile'))
class Caller
  @@ep=Profile.endpoints
  @@ci=Profile.client_info
  
  def initialize
    @@headers={"content-Type"=>"html/text"}
  end
  
  def call(requesth)
    req_data="#{Hash2urlHelper.hash2cgiString(requesth)}&#{Hash2urlHelper.hash2cgiString(@@ci)}"
    http=Net::HTTP.new(@@ep["SERVER"],443)
    http.use_ssl=(true)
    http.timeout=600
    contents, unparsedata = http.post2(@@ep["SERVICE"],req_data,@@headers)
    data=CGI::parse(unparsedata)
    transaction = Transaction.new(data)
  end

end