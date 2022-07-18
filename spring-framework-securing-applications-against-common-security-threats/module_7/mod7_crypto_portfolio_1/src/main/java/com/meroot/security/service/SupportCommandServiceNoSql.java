package com.meroot.security.service;

import com.meroot.security.model.CreateSupportQueryDto;
import com.meroot.security.util.AuthenticationUtil;
import org.springframework.stereotype.Service;

import com.meroot.security.entity.Post;
import com.meroot.security.entity.SupportQuery;
import com.meroot.security.model.PostDto;
import com.meroot.security.repository.SupportQueryRepository;

@Service
public class SupportCommandServiceNoSql implements SupportCommandService {

	private final SupportQueryRepository supportRepository;
		
	public SupportCommandServiceNoSql(SupportQueryRepository supportRepository) {
		this.supportRepository = supportRepository;
	}

	@Override
	public void createQuery(CreateSupportQueryDto query) {
		supportRepository.save(mapModelToEntity(query));
	}
	
	@Override
	public void postToQuery(PostDto model) {
		Post post = new Post(AuthenticationUtil.getUsername() , model.getContent(), System.currentTimeMillis());
		SupportQuery query = supportRepository.findById(model.getQueryId()).get();
		query.addPost(post);
		if(model.isResolve()) {
			query.resolve();
		}
		supportRepository.save(query);
	}
	
	@Override
	public void resolveQuery(String id) {
		SupportQuery query = supportRepository.findById(id).get();
		query.resolve();
		supportRepository.save(query);
	}
	
	private SupportQuery mapModelToEntity(CreateSupportQueryDto model) {
		SupportQuery supportQuery = new SupportQuery(AuthenticationUtil.getUsername() , model.getSubject());
		supportQuery.addPost(model.getContent(), AuthenticationUtil.getUsername() );
		return supportQuery;
	}
		
}
