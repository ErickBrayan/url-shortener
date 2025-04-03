package com.eb2.urlshorteningservice.service;

import com.eb2.urlshorteningservice.entities.Shorten;
import com.eb2.urlshorteningservice.repository.ShortenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ShortenRepository repository;

    @KafkaListener(topics = "url", groupId = "groupId")
    void listener (String shortCode) {
        updateStats(shortCode);
    }

    private void updateStats(String shortCode) {

        repository.findByShortCode(shortCode).ifPresent(s -> {
            int accessCount = s.getAccessCount();
            s.setAccessCount(accessCount + 1);
            repository.save(s);
        });

    }



}
