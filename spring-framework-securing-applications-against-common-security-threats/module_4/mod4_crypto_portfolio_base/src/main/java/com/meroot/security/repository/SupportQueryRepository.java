package com.meroot.security.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.meroot.security.entity.SupportQuery;

public interface SupportQueryRepository extends MongoRepository<SupportQuery, String>{
	
	List<SupportQuery> findByUsername(String username);
	
}
