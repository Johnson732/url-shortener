package com.shorty.urlshortener.service;

import com.shorty.urlshortener.entities.UrlMapping;
import com.shorty.urlshortener.repository.UrlMappingRepository;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class ShortlyService {

    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String BASE_URL = "http://localhost:8080/shortly/";
    private final UrlMappingRepository repository;

    public ShortlyService(UrlMappingRepository repository){
        this.repository = repository;
    }

    public String createShortUrl(String longUrl){

        UrlMapping mapping = new UrlMapping();
        mapping.setLongUrl(longUrl);

        mapping = repository.save(mapping);

        String shortCode = encodeBase62(mapping.getId());

        mapping.setShortUrl(shortCode);

        repository.save(mapping);

        return BASE_URL + shortCode;
    }

    public String getOriginalUrl(String shortCode){
        UrlMapping mapping = repository.findByShortCode(shortCode);

        if(mapping == null){
            throw new RuntimeException("Short URL not found");
        }

        return mapping.getLongUrl();
    }

    private String encodeBase62(long id){
        StringBuilder encoded = new StringBuilder();

        while(id>0){
            int remainder = (int)(id%62);
            encoded.append(BASE62.charAt(remainder));
            id = id/62;
        }
        return encoded.reverse().toString();
    }
}
