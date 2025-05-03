package com.eb2.urlshorteningservice.controller;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/cache")
@Log4j2
@RequiredArgsConstructor
public class CacheController {

    private final CacheManager cacheManager;

    @GetMapping("/{cache-name}")
    public Map<Object, Object> cacheName(@PathVariable("cache-name") String cacheName) {
        log.info("Calling cache name: {}", cacheName);
        com.github.benmanes.caffeine.cache.Cache<Object, Object> nativeCache = (Cache<Object, Object>) cacheManager.getCache(cacheName).getNativeCache();
        return nativeCache.asMap();
    }

    @DeleteMapping("/{cache-name}/clear")
    public void clear(@PathVariable("cache-name") String cacheName) {
        log.info("Calling clean cache name: {}", cacheName);
        Optional.ofNullable(cacheName).flatMap(name -> Optional.ofNullable(this.cacheManager.getCache(name)))
                .ifPresent(org.springframework.cache.Cache::clear);
    }
}
