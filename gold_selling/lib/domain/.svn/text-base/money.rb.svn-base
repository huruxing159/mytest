# Understands a particular barter value
class Money
  include Comparable

  attr_reader :amount, :currency
  protected :currency
  
  def initialize(amount, currency)
    @amount, @currency = amount, currency
  end
  
  def ==(other)
    return false unless other.is_a? Money
    (@amount - converted_amount(other)).abs < 0.0001
  end
  
  def <=>(other)
    return 0 if self == other
    self.amount <=> converted_amount(other)
  end
  
  def /(constant)
    Money.new(@amount/constant.to_f, @currency)
  end
  
  def *(constant)
    Money.new(@amount*constant,@currency)
  end
  
  def in(new_currency)
    Money.new(new_currency.amount_from(@amount, @currency), new_currency)
  end
  
  def show(display = 2)
    @currency.show(@amount,display)
  end
  def Money.format_show_amount(amount,display=2)
    sprintf("%.#{display}f",amount)  
  end

  private
  
    def converted_amount(other)
      self.currency.amount_from(other.amount, other.currency)
    end
  
end
