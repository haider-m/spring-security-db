package com.haider.springsecuritydb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * Encodes new passwords with the algorithm chosen by {@code app.security.password-encoder}
 * (bcrypt | pbkdf2 | scrypt | argon2; default bcrypt). Hashes are stored with an "{id}" prefix,
 * so previously stored hashes keep working after the setting changes.
 */
@Configuration
public class PasswordEncoderConfig {

    @Bean
    public PasswordEncoder passwordEncoder(
            @Value("${app.security.password-encoder:bcrypt}") String encodingId) {

        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("pbkdf2", Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8());
        encoders.put("scrypt", SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8());
        encoders.put("argon2", Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8());

        if (!encoders.containsKey(encodingId)) {
            throw new IllegalArgumentException(
                    "Unsupported app.security.password-encoder '" + encodingId + "'; use one of " + encoders.keySet());
        }
        return new DelegatingPasswordEncoder(encodingId, encoders);
    }
}
