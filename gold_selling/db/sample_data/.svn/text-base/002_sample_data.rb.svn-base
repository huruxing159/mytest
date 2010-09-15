class CurrencyData < Sequel::Migration
  def up
    DB[:currencies].insert(
                 :label =>"RMB",
                 :symbol=>'¥',
                 :scaling_factor=>1.0
    )
    DB[:currencies].insert(
                 :label =>"GBP",
                 :symbol=>'£',
                 :scaling_factor=>10.0
    )
    DB[:currencies].insert(
                 :label =>"USD",
                 :symbol=>'$',
                 :scaling_factor=>6.5
    )
    DB[:currencies].insert(
                 :label =>"EUR",
                 :symbol=>'€',
                 :scaling_factor=>6.8
    )
  end
  
  def down
    [
      :currencies
    ].each do |table_name|
      begin
        DB[table_name].truncate
      rescue Sequel::DatabaseError => e
        puts e
      end
    end
  end
  
  
end

