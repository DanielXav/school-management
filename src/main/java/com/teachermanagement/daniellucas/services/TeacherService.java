package com.teachermanagement.daniellucas.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.teachermanagement.daniellucas.dto.TeacherDTO;
import com.teachermanagement.daniellucas.exceptions.ResourceNotFoundException;
import com.teachermanagement.daniellucas.models.TeacherModel;
import com.teachermanagement.daniellucas.repositories.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
	private TeacherRepository repository;
	
	public List<TeacherDTO> findAll(){
		return repository.findAll().stream().map(x -> new TeacherDTO(x)).toList();
	}
	
	public TeacherDTO findById(Long id) {
		return new TeacherDTO(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Teacher")));
	}
	
	public TeacherDTO insert(TeacherDTO dto) {
		TeacherModel entity = new TeacherModel();
		BeanUtils.copyProperties(dto, entity);
		repository.save(entity);
		return new TeacherDTO(entity);
	}
	
	public TeacherDTO insert(Long id, TeacherDTO dto) {
		try {
			TeacherModel entity = repository.findById(id).get();
			BeanUtils.copyProperties(dto, entity);
			repository.save(entity);
			return new TeacherDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Teacher id: " + id);
		}
	} 
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Teacher id: " + id);
		}
	}
}
