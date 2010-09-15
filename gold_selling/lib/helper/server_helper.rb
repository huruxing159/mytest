class ServerHelper
  
  def ServerHelper.game_server(server_name,faction_name)
    server=DB[:servers][:name =>server_name,:faction=>faction_name] 
  end
  
  def ServerHelper.gold_amount(server_name,faction_name)
      server =game_server(server_name,faction_name)
      return 0 if server.nil?
      storage=DB[:storagelists].filter(:serverid=>server[:id])
      total = storage.inject(0) {|result, element| result + element[:amount]}
  end 

  def ServerHelper.gold_unit_price(server_name,faction_name)
    server =game_server(server_name,faction_name)
    server ? server[:current_unit_price] : 0.to_f
  end  

end