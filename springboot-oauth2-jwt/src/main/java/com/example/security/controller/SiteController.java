package com.example.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.model.SiteModel;
import com.example.security.service.SiteService;

@RestController
public class SiteController {
	
	@Autowired
	private SiteService siteService;
	
	@PostMapping("/api/sites")
	public SiteModel createSite(@RequestBody SiteModel siteModel) {
		
		return siteService.createSite(siteModel);
				
	}

}
