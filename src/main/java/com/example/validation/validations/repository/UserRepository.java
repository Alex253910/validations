package com.example.validation.validations.repository;

import com.example.validation.validations.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
