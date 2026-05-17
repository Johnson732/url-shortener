package com.shorty.urlshortener.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class ShortlyInMemoryService {

    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String BASE_URL = "http://localhost:8080/";
    private final Map<String, String> urlStore = new HashMap<>();
    private long counter = 1;
    public String createShortUrl(String longUrl){

        long id = counter++;

        String shortCode = encodeBase62(id);

        urlStore.put(shortCode, longUrl);

        return BASE_URL + shortCode;
    }

    public String getOriginalUrl(String shortCode){
        String originalUrl = urlStore.get(shortCode);
        if(originalUrl == null){
            throw new RuntimeException("Short URL not found");
        }

        return originalUrl;
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
