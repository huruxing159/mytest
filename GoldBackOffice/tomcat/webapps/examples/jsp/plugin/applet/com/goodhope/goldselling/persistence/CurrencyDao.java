package com.goodhope.goldselling.persistence;

import java.util.List;

import com.goodhope.goldselling.domain.Currency;

public interface CurrencyDao {

	public List<Currency> getAllCurrencies();

}
