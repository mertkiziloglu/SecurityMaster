package com.meroot.security.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.meroot.security.model.AddTransactionToPortfolioDto;
import com.meroot.security.util.AuthenticationUtil;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import com.meroot.security.entity.CryptoCurrency;
import com.meroot.security.entity.Portfolio;
import com.meroot.security.entity.Transaction;
import com.meroot.security.entity.Type;
import com.meroot.security.repository.CryptoCurrencyRepository;
import com.meroot.security.repository.PortfolioRepository;

@Service
public class PortfolioCommandServiceNoSql implements PortfolioCommandService {

	private final PortfolioRepository portfolioRepository;
	private final CryptoCurrencyRepository currencyRepository;
	
	
	
	public PortfolioCommandServiceNoSql(PortfolioRepository portfolioRepository,
			CryptoCurrencyRepository currencyRepository) {
		this.portfolioRepository = portfolioRepository;
		this.currencyRepository = currencyRepository;
	}

	@Override
	public void addTransactionToPortfolio(AddTransactionToPortfolioDto request) {
		Portfolio portfolio = portfolioRepository.findByUsername(AuthenticationUtil.getUsername());
		Transaction transaction = createTransactionEntity(request);
		portfolio.addTransaction(transaction);
		portfolioRepository.save(portfolio);
	}

	@PreFilter("hasRole('AMDIN') or filterObject.username == authentication.username")
	public void addTransactionToPortfolio(List<AddTransactionToPortfolioDto> transactionsDto) {
		Portfolio portfolio = portfolioRepository.findByUsername(AuthenticationUtil.getUsername());
		for(AddTransactionToPortfolioDto transactionDto : transactionsDto) {
			Transaction transaction = createTransactionEntity(transactionDto);
			portfolio.addTransaction(transaction);
		}
		portfolioRepository.save(portfolio);
	}
	
	@Override
	public void removeTransactionFromPortfolio(String username, String transactionId) {
		Portfolio portfolio = this.portfolioRepository.findByUsername(username);
		Transaction transacion = portfolio.getTransactionById(transactionId);
		portfolio.deleteTransaction(transacion);
		portfolioRepository.save(portfolio);
	}
	
	@Override
	public void createNewPortfolio(String username) {
		portfolioRepository.save(new Portfolio(username, new ArrayList<>()));
	}
	
	private Transaction createTransactionEntity(AddTransactionToPortfolioDto request) {
		CryptoCurrency crpytoCurrency = currencyRepository.findBySymbol(request.getCryptoSymbol());
		Type type = Type.valueOf(request.getTransactionType());
		BigDecimal quantity = new BigDecimal(request.getQuantity());
		BigDecimal price = new BigDecimal(request.getPrice());
		Transaction transaction = new Transaction(crpytoCurrency, type, quantity, price,System.currentTimeMillis());
		return transaction;
	}

	@Override
	public boolean userHasAportfolio(String username) {
		return this.portfolioRepository.existsByUsername(username);
	}

}
