require 'digest/md5'
class Md5Helper
  
  def Md5Helper.md5(original)
    Digest::MD5.hexdigest(original)
  end
  
end