package com.meroot.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.meroot.security.entity.CryptoCurrency;
import com.meroot.security.repository.CryptoCurrencyRepository;

@SpringBootApplication
public class WebApplication {

	private final CryptoCurrencyRepository cryptoRepository;
	
	public WebApplication(CryptoCurrencyRepository cryptoRepository) {
		this.cryptoRepository = cryptoRepository;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void intializeUserData() {		
		CryptoCurrency bitcoin = new CryptoCurrency("BTC", "Bitcoin");
		CryptoCurrency litecoin = new CryptoCurrency("LTC", "Litecoin");
		cryptoRepository.save(bitcoin);
		cryptoRepository.save(litecoin);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
	
}