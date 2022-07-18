package com.meroot.security.repository;

import com.meroot.security.entity.CryptoCurrency;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CryptoCurrencyRepository extends MongoRepository<CryptoCurrency, String>{
	
	CryptoCurrency findBySymbol(String symbol);
	
}
