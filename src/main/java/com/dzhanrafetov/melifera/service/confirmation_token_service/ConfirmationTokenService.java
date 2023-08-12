package com.dzhanrafetov.melifera.service.confirmation_token_service;


import com.dzhanrafetov.melifera.model.confirmation_token_model.ConfirmationToken;
import com.dzhanrafetov.melifera.repository.confirmation_token_repository.ConfirmationTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmTokenRepository;

    public ConfirmationTokenService(ConfirmationTokenRepository confirmTokenRepository) {
        this.confirmTokenRepository = confirmTokenRepository;
    }

    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
        confirmTokenRepository.save(confirmationToken);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmTokenRepository.findByToken(token);
    }


    public ConfirmationToken deleteConfirmationtoken(Long userId) {
            confirmTokenRepository.deleteByUserId(userId);

        return null;
    }
}
