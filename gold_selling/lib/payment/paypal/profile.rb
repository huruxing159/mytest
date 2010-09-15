class Profile
  root=File.expand_path(File.dirname(__FILE__))
  config=YAML.load_file(File.join(root, 'paypal.yml'))
  def Profile.credentials
    @@credentials
  end
  
  def Profile.PAYPAL_URL
    @@PAYPAL_URL
  end
  
  def Profile.client_info
    @@client_info
  end
  
  def Profile.endpoints
    @@endpoints
  end
  
  @@PAYPAL_URL = config["PAYPAL_URL"]
  @@credentials={"USER" => config["PAYPALUSER"], "PWD" => config["PWD"], "SIGNATURE" => config["SIGNATURE"] }
  @@endpoints={"SERVER" => config["SERVER"], "SERVICE" => config["SERVICE"]}
  @@client_info={ "VERSION" => config["VERSION"], "SOURCE" => config["SOURCE"]} 
end