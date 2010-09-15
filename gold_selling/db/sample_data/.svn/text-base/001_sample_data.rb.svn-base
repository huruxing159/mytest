# Migration command:
#   sequel 'mysql://root@localhost/gold_selling' -m db/sample_data/ --echo

class ReferenceData < Sequel::Migration
  
  def up
    now = Time.now
    country_id = DB[:countries].filter(:name => 'US').select(:id).single_value
    http = Net::HTTP.new('www.worldofwarcraft.com',80)
    response = http.get('/realmstatus/compat.html',nil)
    if (response.message != 'OK')
      puts "crawl us server failed!"
      return
    end
    response.body.scan(/<td class = "serverStatus\d".+<b.+>(.+?)<\/b>.+<\/td>/).each do |arr|
      server_name = arr.to_s.sub("&#039;","'")
      DB[:servers].insert(
        :name => "#{server_name} - US",
        :country_id => country_id,
        :created_at => now,
        :updated_at => now,
        :faction=>"Alliance",
        :gold_amount => 2000000000
      )
      DB[:servers].insert(
        :name => "#{server_name} - US",
        :country_id => country_id,
        :created_at => now,
        :updated_at => now,
        :faction=>"Hord",
        :gold_amount => 2000000000
      )
    end
    
    country_id = DB[:countries].filter(:name => 'EU').select(:id).single_value
    http = Net::HTTP.new('www.wow-europe.com',80)
    response = http.get('/realmstatus/index.html',nil)
    if (response.message != 'OK')
      puts "crawl eu server failed!"
      return
    end
    response.body.scan(/<td class = "serverStatus\d".+<a name="(.+?)">/).each do |arr|
      DB[:servers].insert(
        :name => "#{arr} - EU",
        :country_id => country_id,
        :created_at => now,
        :updated_at => now,
        :faction=>"Alliance",
        :gold_amount => 2000000000
        
      )
      DB[:servers].insert(
        :name => "#{arr} - EU",
        :country_id => country_id,
        :created_at => now,
        :updated_at => now,
        :faction=>"Hord",
        :gold_amount => 2000000000
        
      )
    end
    
    
  end
  
  def down
    [
      :servers
    ].each do |table_name|
      begin
        DB[table_name].truncate
      rescue Sequel::DatabaseError => e
        puts e
      end
    end
  end
  
end