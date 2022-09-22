package com.teachermanagement.daniellucas.services;

import com.teachermanagement.daniellucas.dto.SubjectDTO;
import com.teachermanagement.daniellucas.exceptions.ResourceNotFoundException;
import com.teachermanagement.daniellucas.models.SubjectModel;
import com.teachermanagement.daniellucas.repositories.SubjectRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository repository;

    public List<SubjectDTO> findAll(){
        return repository.findAll().stream().map(x -> new SubjectDTO(x)).toList();
    }

    public SubjectDTO findById(Long id) {
        return new SubjectDTO(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subject")));
    }

    public SubjectDTO insert(SubjectDTO dto) {
        SubjectModel entity = new SubjectModel();
        BeanUtils.copyProperties(dto, entity);
        repository.save(entity);
        return new SubjectDTO(entity);
    }

    public SubjectDTO update(Long id, SubjectDTO dto) {
        try {
            SubjectModel entity = repository.findById(id).get();
            entity.setName(dto.getName());
            entity.setRoom(dto.getRoom());
            repository.save(entity);
            return new SubjectDTO(entity);
        }
        catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException("Subject id: " + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Subject id: " + id);
        }
    }
}
