# Understands the unit of barter for a country
require File.expand_path(File.join(File.dirname(__FILE__), 'money'))

class Currency < Sequel::Model

  def amount_from(other_amount, other_unit)
    other_amount * other_unit.scaling_factor / self.scaling_factor
  end
  
  
  def show(amount,display)
    sprintf("%s%.#{display}f",self.symbol,amount)
  end
  
  def Currency.get_currency_by_name(currency)
     begin
        Currency.find(:label=>currency.upcase)       
     rescue
       $log.error("could not find currency #{currency}")
       raise
     end
  end
  
  
  private
  
   def self.currency(label)
      currency_method_name = label.downcase.to_sym
      Numeric.send(:define_method, currency_method_name, Proc.new { currency = Currency.find(:label=>label);Money.new(self, currency) } )
   end
  
    Currency.dataset.each{  |cur|   currency(cur[:label])}
      
end
