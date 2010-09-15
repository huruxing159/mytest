class NumberFormater
  def NumberFormater.format(number)
    number.gsub(/(\d)(?=\d{3}+(?:\.|$))(\d{3}\..*)?/,'\1,\2')#money format like 2000,000,000.00
  end
end