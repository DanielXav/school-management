package com.teachermanagement.daniellucas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.teachermanagement.daniellucas.models.UserModel;
import com.teachermanagement.daniellucas.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void signUpUser(UserModel user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
