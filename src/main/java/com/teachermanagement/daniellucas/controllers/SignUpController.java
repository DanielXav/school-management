package com.teachermanagement.daniellucas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teachermanagement.daniellucas.dto.UserDTO;
import com.teachermanagement.daniellucas.mapper.UserMapper;
import com.teachermanagement.daniellucas.services.UserService;

//import br.edu.uepb.coffee.mapper.UserMapper;
import io.swagger.annotations.Api;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
@Api(value = "Sign Up")
public class SignUpController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;
 
    @PostMapping("/signup")
    public void signUp(@RequestBody UserDTO userDTO){
        userService.signUpUser(userMapper.convertFromUserDTO(userDTO));
    }
}