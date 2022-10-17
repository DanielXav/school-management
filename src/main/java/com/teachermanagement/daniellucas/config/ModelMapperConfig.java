package com.teachermanagement.daniellucas.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.teachermanagement.daniellucas.mapper.UserMapper;

@Configuration
public class ModelMapperConfig {

	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }
}
