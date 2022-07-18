package com.meroot.security.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.meroot.security.entity.CryptoCurrency;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CryptoCurrencyRepositoryTest {

	@Autowired
	private CryptoCurrencyRepository repository;
	
	@Before
	public void setup() {
		repository.deleteAll();
	}
	
	@Test
	public void getAllSupportedCryptoCurrencies() {
		//Given
		repository.save(new CryptoCurrency("BTC","Bitcoin"));
		repository.save(new CryptoCurrency("LTC", "Litecoin"));
		//When
		List<CryptoCurrency> cryptos = repository.findAll();
		//Then
		assertEquals(2, cryptos.size());
		assertTrue(cryptos.get(0).getSymbol().equals("BTC") || cryptos.get(1).getSymbol().equals("BTC"));
	}
	
}
