package com.example.forumComet.repository;

import com.example.forumComet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);
Optional<User> findByEmail(String email);
Optional<User> findByToken(String token);

@Transactional
@Modifying
    @Query("UPDATE User SET active = TRUE, token = NULL WHERE id = :id")
    void confirmById(Long id);

}
