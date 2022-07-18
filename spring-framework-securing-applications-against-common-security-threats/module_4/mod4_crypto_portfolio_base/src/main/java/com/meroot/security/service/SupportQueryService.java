package com.meroot.security.service;

import java.util.List;

import com.meroot.security.model.SupportQueryDto;

public interface SupportQueryService {

	List<SupportQueryDto> getSupportQueriesForUser();
	SupportQueryDto getSupportQueryById(String queryId);
	List<SupportQueryDto> getSupportQueriesForAllUsers();
	
}
