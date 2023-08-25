package com.dzhanrafetov.melifera.repository;


import com.dzhanrafetov.melifera.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {
    Optional<ConfirmationToken> findByToken(String token);
    @Transactional
    void deleteByUserId(Long userId);

    void deleteByCreatedAtBefore(LocalDateTime timestamp);


}
