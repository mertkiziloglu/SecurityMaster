package com.meroot.security.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.meroot.security.annotations.FilterOutNonCurrentUser;
import com.meroot.security.entity.SupportQuery;
import com.meroot.security.model.PostDto;
import com.meroot.security.model.SupportQueryDto;
import com.meroot.security.repository.SupportQueryRepository;

@Service
public class SupportQueryServiceNoSql implements SupportQueryService {

	private final SupportQueryRepository supportRepository;
	
	public SupportQueryServiceNoSql(SupportQueryRepository supportRepository) {
		this.supportRepository = supportRepository;
	}

	@Override
	@FilterOutNonCurrentUser
	public List<SupportQueryDto> getSupportQueriesForUser() {
		List<SupportQuery> supportQueries = supportRepository.findByUsername(getUsername());
		return mapEntityToModel(supportQueries);
	}

	@Override
	public SupportQueryDto getSupportQueryById(String id) {
		return mapEntityToModel(this.supportRepository.findById(id).get());
	}
	
	@Override
	public List<SupportQueryDto> getSupportQueriesForAllUsers() {
		List<SupportQuery> supportQueries = this.supportRepository.findAll();
		return mapEntityToModel(supportQueries);
	}
	@FilterOutNonCurrentUser
	private SupportQueryDto mapEntityToModel(SupportQuery supportQuery) {
		List<PostDto> posts = supportQuery.getPosts().stream().map(post -> {
			return new PostDto(post.getId(), post.getContent(),post.getUsername(),supportQuery.isResolved());
		}).collect(Collectors.toList());
		return new SupportQueryDto(supportQuery.getId(), supportQuery.getSubject(), supportQuery.getCreated(),
				supportQuery.getUsername(), supportQuery.isResolved(), posts);

	}
	
	private String getUsername() {
		Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return principle.toString();
	}
	
	private List<SupportQueryDto> mapEntityToModel(List<SupportQuery> supportQueries) {
		return supportQueries.stream().map(query -> {
			return mapEntityToModel(query);
		}).collect(Collectors.toList());
	}

}