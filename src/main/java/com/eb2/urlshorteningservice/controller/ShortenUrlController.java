package com.eb2.urlshorteningservice.controller;

import com.eb2.urlshorteningservice.entities.RequestDto;
import com.eb2.urlshorteningservice.entities.Shorten;
import com.eb2.urlshorteningservice.repository.ShortenRepository;
import com.eb2.urlshorteningservice.service.ShortenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ShortenUrlController {

    private final ShortenService shortenService;
    private final ShortenRepository shortenRepository;

    @PostMapping("/shorten")
    public Shorten shorten (@RequestBody RequestDto request) {


        return shortenService.createShortenUrl(request);
    }

    @GetMapping("/shorten/{shortCode}")
    public Shorten shorten (@PathVariable String shortCode) {
        return shortenService.getShortenByShortCode(shortCode);
    }

    @PatchMapping("/shorten/{shortCode}")
    public Shorten updateShorten (@PathVariable String shortCode, @RequestBody RequestDto request) {
        return shortenService.updateUrl(shortCode, request);
    }

    @DeleteMapping("/shorten/{shortenCode}")
    public ResponseEntity<Void> deleteShorten (@PathVariable String shortenCode) {

        shortenService.deleteShorten(shortenCode);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<?> redirectUrl(@PathVariable String shortCode) {
        Shorten shorten = shortenService.getShortenByShortCode(shortCode);

        String url = shorten.getUrl();
        shorten.setAccessCount(shorten.getAccessCount());
        shortenRepository.save(shorten);


        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", url)
                .build();
    }

    @GetMapping("/{shortCode}/stats")
    public ResponseEntity<?> stats (@PathVariable String shortCode) {
        Shorten shorten = shortenService.getShortenByShortCode(shortCode);




        return ResponseEntity.ok(shorten);
    }
}
