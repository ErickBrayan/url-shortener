package com.eb2.urlshorteningservice.repository;

import com.eb2.urlshorteningservice.entities.Shorten;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Registered
public interface ShortenRepository extends JpaRepository<Shorten, Long> {

    Optional<Shorten> findByShortCode(String shortCode);

    boolean existsShortenByShortCode(String shortCode);

}
