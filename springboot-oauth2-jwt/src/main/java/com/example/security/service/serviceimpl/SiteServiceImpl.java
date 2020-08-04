package com.example.security.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security.model.SiteModel;
import com.example.security.repository.SiteRepository;
import com.example.security.repository.entity.Site;
import com.example.security.service.SiteService;

@Service
public class SiteServiceImpl implements SiteService {

	@Autowired
	private SiteRepository siteRepository;

	@Override
	public SiteModel createSite(SiteModel siteModel) {

		Site site = new Site(null, siteModel.getName(), siteModel.getAddress(), siteModel.getZip());
		siteRepository.save(site);
		return siteModel;
	}

}
