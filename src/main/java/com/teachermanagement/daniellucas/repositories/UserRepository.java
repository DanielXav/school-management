package com.teachermanagement.daniellucas.repositories;

import com.teachermanagement.daniellucas.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
}