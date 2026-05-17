package com.shorty.urlshortener.controller;

import com.shorty.urlshortener.models.LongUrl;
import com.shorty.urlshortener.service.ShortlyInMemoryService;
import com.shorty.urlshortener.service.ShortlyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
//@CrossOrigin(origins = "http://localhost:5173")
public class ShortlyController {
    //private final ShortlyService shortlyService;
    private final ShortlyInMemoryService shortlyInMemoryService;
    public ShortlyController(ShortlyInMemoryService shortlyInMemoryService){
        //this.shortlyService = shortlyService;
        this.shortlyInMemoryService = shortlyInMemoryService;
    }

    /*
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
*/
    // below two endpoints for mvp in-memory purpose
    @PostMapping("/shorten1")
    public String shortenUrl1(@RequestBody LongUrl request){
        return shortlyInMemoryService.createShortUrl(request.longUrl);
    }

    @GetMapping("/{shortCode1}")
    public ResponseEntity<Void> redirect1(@PathVariable String shortCode1){
        String originalUrl = shortlyInMemoryService.getOriginalUrl(shortCode1);

        return ResponseEntity
                .status(302)
                .location(URI.create(originalUrl))
                .build();
    }
}
