package com.meroot.security.service;

import java.util.List;

import com.meroot.security.model.CryptoCurrencyDto;

public interface CurrencyQueryService {

	List<CryptoCurrencyDto> getSupportedCryptoCurrencies();
	CryptoCurrencyDto getCryptoCurrency(String symbol);
}
