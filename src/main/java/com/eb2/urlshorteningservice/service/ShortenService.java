package com.eb2.urlshorteningservice.service;

import com.eb2.urlshorteningservice.entities.RequestDto;
import com.eb2.urlshorteningservice.entities.Shorten;


public interface ShortenService {

    Shorten createShortenUrl(RequestDto requestDto);
    Shorten getShortenByShortCode(String shortCode);
    Shorten updateUrl(String shortCode, RequestDto requestDto);
    void deleteShorten(String shortCode);
}
