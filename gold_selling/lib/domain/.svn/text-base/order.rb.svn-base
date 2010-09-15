class Order <Sequel::Model
 
  def Order.create_new_order(purchase_attemp)
     order=Order.create(
                        :purchase_attemp_id=>purchase_attemp.id,
                        :createtime=>Time.now
                        )
  end
  
  
end