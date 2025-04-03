package com.eb2.urlshorteningservice.utils;

import com.eb2.urlshorteningservice.repository.ShortenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Component
@RequiredArgsConstructor
public class ShortCodeUtils {

    private final ShortenRepository shortenRepository;
    private static final AtomicLong COUNTER = new AtomicLong(100000000000L);
    private static final String ELEMENTS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new Random();

    public String generateShortCode(String longUrl) {

        long uniqueId = getNextCounter();
        String shortCode = base10ToBase62(uniqueId);

        while (shortenRepository.existsShortenByShortCode(shortCode)) {
            uniqueId = getNextCounter();
            shortCode = base10ToBase62(uniqueId);
        }

        return shortCode;
    }

    private String base10ToBase62(Long n) {

        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            sb.insert(0, ELEMENTS.charAt((int) (n % 62)));
            n /= 62;
        }

        return sb.toString();
    }

    private long getNextCounter() {
        return COUNTER.getAndIncrement() + RANDOM.nextInt(9999);
    }



}
