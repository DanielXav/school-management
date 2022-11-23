package com.teachermanagement.daniellucas.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.teachermanagement.daniellucas.dto.ProjectDTO;
import com.teachermanagement.daniellucas.exceptions.ResourceNotFoundException;
import com.teachermanagement.daniellucas.models.ProjectModel;
import com.teachermanagement.daniellucas.repositories.ProjectRepository;
import com.teachermanagement.daniellucas.repositories.UserRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;
    
    @Autowired
    private AuthService authService;

    public List<ProjectDTO> findAll(){
        return repository.findAll().stream().map(x -> new ProjectDTO(x)).toList();
    }

    public ProjectDTO findById(Long id) {
        return new ProjectDTO(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student")));
    }

    public ProjectDTO insert(ProjectDTO dto) {

    	authService.validateSelfOrAdmin();
        ProjectModel entity = new ProjectModel();
        BeanUtils.copyProperties(dto, entity);
        repository.save(entity);
        return new ProjectDTO(entity);
    }

//    public ProjectDTO update(Long id, ProjectDTO dto) {
//        try {
//        	ProjectModel entity = repository.findById(id).get();
//            entity.setName(dto.getName());
//            entity.setDescription(dto.getDescription());
//            entity.setTeacher();
//            repository.save(entity);
//            return new StudentDTO(entity);
//        }
//        catch(EntityNotFoundException e) {
//            throw new ResourceNotFoundException("Student id: " + id);
//        }
//    }
//
//    public void delete(Long id) {
//        try {
//            repository.deleteById(id);
//        }
//        catch (EmptyResultDataAccessException e) {
//            throw new ResourceNotFoundException("Student id: " + id);
//        }
//    }
}
