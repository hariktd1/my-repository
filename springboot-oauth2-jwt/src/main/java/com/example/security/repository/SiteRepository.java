package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.security.repository.entity.Site;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long>{

}
