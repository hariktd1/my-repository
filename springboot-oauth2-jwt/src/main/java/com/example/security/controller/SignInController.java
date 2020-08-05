package com.example.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.model.SiteModel;
import com.example.security.service.SiteService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SiteController {

	
	@Autowired
	private SiteService siteService;

	@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
	@PostMapping("/api/sites")
	public SiteModel createSite(@RequestBody SiteModel siteModel, OAuth2Authentication authentication) {

		String auth = (String) authentication.getUserAuthentication().getPrincipal();
		String role = authentication.getAuthorities().iterator().next().getAuthority();

		log.info("Logged in User : {} with role : {} ", auth, role);
		
		siteModel.setUserName(auth);
		return siteService.createSite(siteModel);

	}

	@GetMapping("/api/sites")
	public List<SiteModel> getSites(OAuth2Authentication authentication) {

		String auth = (String) authentication.getUserAuthentication().getPrincipal();
		String role = authentication.getAuthorities().iterator().next().getAuthority();

		log.info("Logged in User : {} with role : {} ", auth, role);
		
		return siteService.getSites(role,auth);
	}

}
