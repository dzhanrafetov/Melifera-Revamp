package com.dzhanrafetov.melifera.repository.user_repository;


import com.dzhanrafetov.melifera.model.user_model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

;import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByMail(String mail);
    Optional<User> findByUsername(String username);

    List<User> findUserByIsActiveFalse();

    List<User> findUserByIsActiveTrue();



}
