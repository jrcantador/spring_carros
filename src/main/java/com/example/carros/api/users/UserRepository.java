package com.example.carros.api.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByLogin(String login);
}
