package com.example.security.service;

import java.util.List;

import com.example.security.model.SiteModel;

public interface SiteService {

	public SiteModel createSite(SiteModel siteModel);

	public List<SiteModel> getSites(String role, String user);

}
