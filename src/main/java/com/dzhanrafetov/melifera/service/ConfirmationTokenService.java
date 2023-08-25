package com.dzhanrafetov.melifera.service;

import com.dzhanrafetov.melifera.configuration.TokenConfig;
import com.dzhanrafetov.melifera.model.ConfirmationToken;
import com.dzhanrafetov.melifera.repository.ConfirmationTokenRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmTokenRepository;
    private final TokenConfig tokenConfig;


    public ConfirmationTokenService(ConfirmationTokenRepository confirmTokenRepository, TokenConfig tokenConfig) {
        this.confirmTokenRepository = confirmTokenRepository;
        this.tokenConfig = tokenConfig;
    }

    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
        confirmTokenRepository.save(confirmationToken);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmTokenRepository.findByToken(token);
    }

    public void deleteConfirmationtoken(Long userId) {
        confirmTokenRepository.deleteByUserId(userId);
    }
    @Transactional
    @Scheduled(fixedRateString = "#{@tokenConfig.tokenExpirationMillis()}")
    public void deleteExpiredTokens() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        confirmTokenRepository.deleteByCreatedAtBefore(currentDateTime);
    }


}
