package com.teachermanagement.daniellucas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teachermanagement.daniellucas.exceptions.ForbiddenException;
import com.teachermanagement.daniellucas.exceptions.UnauthorizedException;
import com.teachermanagement.daniellucas.models.UserModel;
import com.teachermanagement.daniellucas.repositories.UserRepository;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public UserModel authenticated() {
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			return userRepository.findByEmail(username);
		}
		catch (Exception e) {
			throw new UnauthorizedException("Invalid user");
		}
	}
	
//	public void validateSelfOrAdmin() {
//		UserModel user = authenticated();
//		if (!user.hasHole("ROLE_ADMIN")) {
//			throw new ForbiddenException("Access denied");
//		}
//	}
}
