# Migration command:
#   sequel 'mysql://root@localhost/gold_selling' -m db/migrate/ --echo

class BaseTables < Sequel::Migration
  
  def up
    
    DB.create_table? :countries do
      primary_key   :id
      String        :name, :null => false
      DateTime      :created_at, :null => false
      DateTime      :updated_at, :null => false      
    end
    
    DB.create_table? :servers do
         primary_key   :id
         String        :name, :null => false
         String        :faction ,:null=>false
         foreign_key   :country_id
         DateTime      :created_at, :null => false
         DateTime      :updated_at, :null => false
         Fixnum        :gold_amount
         Float         :history_unit_price ,:default=> 0.00011
         Float         :current_unit_price ,:default=> 0.00012
         String        :history_price_strategy ,:default=>'LinearPrice'
         String        :current_price_strategy ,:default=>'LinearPrice'
    end
    
    
    DB.create_table? :currencies do
       primary_key    :id
       String         :label
       String         :symbol
       Float          :scaling_factor
    end
    
    DB.create_table? :purchase_attemps do
      primary_key   :id
      String        :gh_transaction_id
      String        :region, :null=>false
      String        :faction, :null=>false
      String        :server, :null=>false
      String        :character, :null=>false
      String        :trade_method, :null=>false
      Fixnum        :gold_amount
      Float         :price 
      String        :concurrency
      String        :first_name, :null=>false
      String        :last_name, :null=>false
      String        :email, :null=>false
      String        :phone_number, :null=>false
      String        :country, :null=>false
      String        :payment_method ,:null=>false
      Fixnum        :payment_method_id
      DateTime      :create_time
    end
    
    DB.create_table? :paypal_records do
      primary_key   :id
      String        :paypal_token
      String        :paypal_payer_id
      String        :paypal_transaction_id
      DateTime      :create_time
      
    end
    
    DB.create_table? :moneybookers_records do
       primary_key   :id
       String        :merchant_id
       String        :md5sig
       String        :amount
       String        :transaction_id
       String        :pay_to_email
       String        :mb_currency
       String        :mb_amount
       String        :mb_transaction_id
       String        :status
       String        :currency
       String        :customer_id
       String        :payment_type
       String        :pay_from_email
       DateTime      :create_time
    end

    
    DB.execute(%Q{
      ALTER TABLE servers
      ADD CONSTRAINT servers_FK1
      FOREIGN KEY servers_FK1 (country_id)
      REFERENCES countries (id) ON DELETE CASCADE
    })
    
  end
  
  def down
    [
      :servers, :countries, :purchase_attemps, :paypal_records, :moneybookers_records,:currencies
    ].each do |table_name|
      begin
        DB.drop_table table_name
      rescue Sequel::DatabaseError => e
        puts e
      end
    end
  end
  
end