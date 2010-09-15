# Migration command:
#   sequel 'mysql://root@localhost/gold_selling' -m db/migrate/ --echo

class ReferenceData < Sequel::Migration
  
  def up
    
    now = Time.now
    ['US', 'EU'].each do |country_name|
      DB[:countries].insert(
        :name => country_name,
        :created_at => now,
        :updated_at => now
      )
    end
    
  end
  
  def down
    [
      :countries
    ].each do |table_name|
      begin
        DB[table_name].truncate
      rescue Sequel::DatabaseError => e
        puts e
      end
    end
  end
  
end