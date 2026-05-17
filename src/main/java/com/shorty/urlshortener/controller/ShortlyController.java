package com.shorty.urlshortener.controller;

import com.shorty.urlshortener.models.LongUrl;
import com.shorty.urlshortener.service.ShortlyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("shortly")
@CrossOrigin(origins = "http://localhost:5173")
public class ShortlyController {
    private final ShortlyService shortlyService;

    public ShortlyController(ShortlyService shortlyService){
        this.shortlyService = shortlyService;
    }

    @PostMapping("/shorten")
    public String shortenUrl(@RequestBody LongUrl request){
        return shortlyService.createShortUrl(request.longUrl);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode){
        String originalUrl = shortlyService.getOriginalUrl(shortCode);

        return ResponseEntity
                .status(302)
                .location(URI.create(originalUrl))
                .build();
    }
}
