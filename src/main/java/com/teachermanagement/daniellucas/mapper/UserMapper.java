package com.teachermanagement.daniellucas.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.teachermanagement.daniellucas.dto.UserDTO;
import com.teachermanagement.daniellucas.models.UserModel;

public class UserMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public UserModel convertFromUserDTO(UserDTO userDTO) {
		UserModel userModel = new UserModel();
		modelMapper.map(userDTO, userModel);
		return userModel;
	}
}
