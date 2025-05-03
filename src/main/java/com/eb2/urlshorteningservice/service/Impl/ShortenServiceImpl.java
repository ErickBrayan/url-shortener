package com.eb2.urlshorteningservice.service.Impl;

import com.eb2.urlshorteningservice.config.CacheConfig;
import com.eb2.urlshorteningservice.entities.RequestDto;
import com.eb2.urlshorteningservice.entities.Shorten;
import com.eb2.urlshorteningservice.exception.UrlNotFoundException;
import com.eb2.urlshorteningservice.repository.ShortenRepository;
import com.eb2.urlshorteningservice.service.ShortenService;
import com.eb2.urlshorteningservice.utils.ShortCodeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShortenServiceImpl implements ShortenService {

    private final ShortenRepository shortenRepository;
    private final ShortCodeUtils shortCodeUtils;

    @Override
    public Shorten createShortenUrl(RequestDto requestDto) {

        Shorten shorten = Shorten
                .builder()
                .shortCode(shortCodeUtils.generateShortCode(requestDto.url()))
                .url(requestDto.url())
                .build();

        return shortenRepository.save(shorten);
    }

    @Override
    @Cacheable(value = CacheConfig.URL_SHORTENING_CACHE, unless = "#result == null")
    public Shorten getShortenByShortCode(String shortCode) {

        Optional<Shorten> byShortCode = shortenRepository.findByShortCode(shortCode);

        return byShortCode.orElseThrow(() -> new UrlNotFoundException("Shorten not found", HttpStatus.NOT_FOUND, "404"));
    }


    @Override
    @CachePut(cacheNames = CacheConfig.URL_SHORTENING_CACHE, key = "#shortCode", unless = "#result == null")
    public Shorten updateUrl(String shortCode, RequestDto requestDto) {

        Shorten byShortCode = shortenRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException("Url doesn't exits", HttpStatus.NOT_FOUND, "404"));

        byShortCode.setUrl(requestDto.url());

        return shortenRepository.save(byShortCode);
    }

    @Override
    public void deleteShorten(String shortCode) {
        shortenRepository.findByShortCode(shortCode).ifPresent(shortenRepository::delete);
    }

}
