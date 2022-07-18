package com.meroot.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.meroot.security.entity.CryptoCurrency;

public interface CryptoCurrencyRepository extends MongoRepository<CryptoCurrency, String>{
	
	CryptoCurrency findBySymbol(String symbol);
	
}
