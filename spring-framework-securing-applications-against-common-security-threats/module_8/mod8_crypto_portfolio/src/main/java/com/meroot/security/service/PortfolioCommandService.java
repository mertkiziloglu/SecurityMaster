package com.meroot.security.service;

import com.meroot.security.model.AddTransactionToPortfolioDto;

public interface PortfolioCommandService {

	void addTransactionToPortfolio(AddTransactionToPortfolioDto request);
	void removeTransactionFromPortfolio(String transactionId);
	void createNewPortfolio(String username);
	boolean userHasAportfolio(String username);
}
