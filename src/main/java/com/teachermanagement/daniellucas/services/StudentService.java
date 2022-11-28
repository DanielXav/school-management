package com.teachermanagement.daniellucas.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.teachermanagement.daniellucas.dto.StudentDTO;
import com.teachermanagement.daniellucas.exceptions.ResourceNotFoundException;
import com.teachermanagement.daniellucas.models.ProjectModel;
import com.teachermanagement.daniellucas.models.StudentModel;
import com.teachermanagement.daniellucas.repositories.ProjectRepository;
import com.teachermanagement.daniellucas.repositories.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;
    
    @Autowired
    private ProjectRepository projectRepository;

    public List<StudentDTO> findAll(){
        return repository.findAll().stream().map(x -> new StudentDTO(x)).toList();
    }

    public StudentDTO findById(Long id) {
        return new StudentDTO(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student")));
    }

    public StudentDTO insert(StudentDTO dto) {
        StudentModel entity = new StudentModel();
        BeanUtils.copyProperties(dto, entity);
        ProjectModel project = projectRepository.findById(dto.getProjectId()).get();
        entity.setProject(project);
        repository.save(entity);
        return new StudentDTO(entity);
    }

    public StudentDTO update(Long id, StudentDTO dto) {
        try {
            StudentModel entity = repository.findById(id).get();
            entity.setName(dto.getName());
            entity.setEmail(dto.getEmail());
            entity.setRegistration(dto.getRegistration());
            repository.save(entity);
            return new StudentDTO(entity);
        }
        catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException("Student id: " + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Student id: " + id);
        }
    }
}
