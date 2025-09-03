package com.fitnessai.userservice.repository;

import com.fitnessai.userservice.models.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {


    public Boolean existsByEmail(String email);
}
