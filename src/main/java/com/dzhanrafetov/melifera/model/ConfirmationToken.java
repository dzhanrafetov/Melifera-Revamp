package com.dzhanrafetov.melifera.model;

import com.dzhanrafetov.melifera.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // Add this property

    private LocalDateTime deletionTime;

    public ConfirmationToken() {
    }

    public ConfirmationToken(String token, User user, LocalDateTime createdAt, LocalDateTime deletionTime) {
        this.token = token;
        this.user = user;
        this.createdAt = createdAt;
        this.deletionTime = deletionTime;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
    // Other getters and setters

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getDeletionTime() {
        return deletionTime;
    }
}
