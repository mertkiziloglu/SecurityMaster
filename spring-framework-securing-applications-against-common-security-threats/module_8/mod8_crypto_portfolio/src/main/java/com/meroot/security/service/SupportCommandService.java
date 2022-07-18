package com.meroot.security.service;

import com.meroot.security.model.CreateSupportQueryDto;
import com.meroot.security.model.PostDto;

public interface SupportCommandService {

	void createQuery(CreateSupportQueryDto query);
	void postToQuery(PostDto supportQueryPostModel);
	void resolveQuery(String id);
	
}
