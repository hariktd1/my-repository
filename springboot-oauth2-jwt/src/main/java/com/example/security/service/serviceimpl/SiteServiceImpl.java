package com.example.security.service.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security.model.SiteModel;
import com.example.security.repository.SiteRepository;
import com.example.security.repository.entity.Site;
import com.example.security.service.SiteService;

@Service
public class SiteServiceImpl implements SiteService {

	private final String USER = "USER";

	private final String ADMIN = "ADMIN";

	@Autowired
	private SiteRepository siteRepository;

	@Override
	public SiteModel createSite(SiteModel siteModel) {

		Site site = new Site(null, siteModel.getName(), siteModel.getAddress(), siteModel.getZip(),
				siteModel.getUserName());
		siteRepository.save(site);
		return siteModel;
	}

	@Override
	public List<SiteModel> getSites(String role, String user) {

		List<SiteModel> siteModelList = new ArrayList<>();
		if (USER.equals(role)) {
			siteModelList = siteRepository.findByUserName(user).stream()
					.map(s -> new SiteModel(s.getId(), s.getName(), s.getAddress(), s.getZip(), s.getUserName()))
					.collect(Collectors.toList());

		} else if (ADMIN.equals(role)) {

			siteModelList = siteRepository.findAll().stream()
					.map(s -> new SiteModel(s.getId(), s.getName(), s.getAddress(), s.getZip(), s.getUserName()))
					.collect(Collectors.toList());

		}
		return siteModelList;

	}

}
