package com.meroot.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.meroot.security.service.SupportQueryService;


@Controller
public class SupportAdminQueryController {

	private final SupportQueryService supportQueryService;
		
	public SupportAdminQueryController(SupportQueryService supportQueryService) {
		this.supportQueryService = supportQueryService;
	}

	@GetMapping("/support/admin")
	public ModelAndView getSupportQueries() {
		return new ModelAndView("support","queries",supportQueryService.getSupportQueriesForAllUsers());
	}
	
}
