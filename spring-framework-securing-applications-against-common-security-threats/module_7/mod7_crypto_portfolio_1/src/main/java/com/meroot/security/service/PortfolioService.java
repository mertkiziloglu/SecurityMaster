package com.meroot.security.service;

import java.util.List;

import com.meroot.security.model.AddTransactionToPortfolioDto;
import com.meroot.security.entity.CryptoCurrency;
import com.meroot.security.entity.Portfolio;
import com.meroot.security.model.ListTransactionsDto;
import com.meroot.security.model.PortfolioPositionsDto;

public interface PortfolioService {

	List<CryptoCurrency> getSupportedCryptoCurrencies();
	Portfolio getPortfolioForUsername(String username);
	PortfolioPositionsDto getPortfolioPositions(String username);
	void addTransactionToPortfolio(AddTransactionToPortfolioDto request);
	ListTransactionsDto getPortfolioTransactions(String username);
	void removeTransactionFromPortfolio(String username, String transactionId);
	void createNewPortfolio(String username);
}
