package com.meroot.security.entity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Portfolio {

	@Id
	private String id;
	private final String username;
	private final List<Transaction> transactions;
	
	public Portfolio(String username, List<Transaction> transactions) {
		this.username = username;
		this.transactions = transactions;
	}
	
	public void addTransaction(Transaction transaction) {
		this.transactions.add(transaction);
	}
	
	public List<Transaction> getTransactions() {
		return Collections.unmodifiableList(transactions);
	}
	
	public List<Transaction> getTransactionsForCoin(String symbol) {
		return transactions.stream().filter(transaction -> 
			transaction.getCryptoCurrency().getSymbol().equals(symbol)).collect(Collectors.toList());
	}
	
	public void deleteTransaction(Transaction transaction) {
		transactions.remove(transaction);
	}
	
	public Transaction getTransactionById(String id) {
		for(Transaction transaction : this.transactions) {
			if(id.equals(transaction.getId())) {
				return transaction;
			}
		}
		return null;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getId() {
		return id;
	}
}
