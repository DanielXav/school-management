package com.teachermanagement.daniellucas.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserModelDetails;
import org.springframework.security.core.userdetails.UserModelDetailsService;
import org.springframework.security.core.userdetails.UserModelnameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teachermanagement.daniellucas.exceptions.ResourceNotFoundException;
import com.teachermanagement.daniellucas.models.RoleModel;
import com.teachermanagement.daniellucas.models.UserModel;
import com.teachermanagement.daniellucas.repositories.UserModelRepository;



@Service
public class UserModelService implements UserModelDetailsService {
	
	private static Logger logger = LoggerFactory.getLogger(UserModelService.class);
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserModelRepository repository;
	
	@Autowired
	private RoleModelRepository roleRepository;
	
	@Transactional(readOnly = true)
	public Page<UserModelDTO> findAllPaged(Pageable pageable){
		Page<UserModelModel> list = repository.findAll(pageable);
		return list.map(x -> new UserModelDTO(x));
	}
	
	@Transactional(readOnly = true)
	public UserModelDTO findById(Long id) {
		Optional<UserModelModel> obj = repository.findById(id);
		UserModel entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new UserModelDTO(entity);
	}
	
	@Transactional
	public UserModelDTO insert(UserModelInsertDTO dto) {
		UserModel entity = new UserModel();
		copyDtoToEntity(dto, entity);
		entity.setPassword(passwordEncoder.encode(dto.getPassword()));
		entity = repository.save(entity);
		return new UserModelDTO(entity);
	}
	
	@Transactional
	public UserModelDTO update(Long id, UserModelUpdateDTO dto) {
		try {
			UserModel entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new UserModelDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	private void copyDtoToEntity(UserModelDTO dto, UserModel entity) {
		
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setEmail(dto.getEmail());
		
		entity.getRoleModels().clear();
		for (RoleModelDTO roleDto : dto.getRoleModels()) {
			RoleModel role = roleRepository.getOne(roleDto.getId());
			entity.getRoleModels().add(role);
		}
	}

	@Override
	public UserModelDetails loadUserModelByUserModelname(String username) throws UserModelnameNotFoundException {
		UserModel user = repository.findByEmail(username);
		if (user == null) {
			logger.error("UserModel not found: " + username);
			throw new UserModelnameNotFoundException("Email not found");
		}
		logger.info("UserModel found: " + username);
		return user;
	}
}
