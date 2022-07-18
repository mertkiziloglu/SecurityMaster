package com.meroot.security.service;

import java.util.List;

import com.meroot.security.model.ListTransactionsDto;
import com.meroot.security.model.PortfolioPositionsDto;

public interface PortfolioQueryService {

	PortfolioPositionsDto getPortfolioPositions();
	PortfolioPositionsDto getPortfolioPositions(String id);
	ListTransactionsDto getPortfolioTransactions();
	List<String> getPortfolioIds();
	PortfolioPositionsDto getPortfolioPositionsForUser(String username);
	
}
