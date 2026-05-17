package com.shorty.urlshortener.repository;

import com.shorty.urlshortener.entities.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
    UrlMapping findByShortCode(String shortCode);
}
