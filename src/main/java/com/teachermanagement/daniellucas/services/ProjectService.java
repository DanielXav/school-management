package com.teachermanagement.daniellucas.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.teachermanagement.daniellucas.dto.ProjectDTO;
import com.teachermanagement.daniellucas.dto.StudentDTO;
import com.teachermanagement.daniellucas.dto.SubjectDTO;
import com.teachermanagement.daniellucas.exceptions.ResourceNotFoundException;
import com.teachermanagement.daniellucas.models.ProjectModel;
import com.teachermanagement.daniellucas.models.StudentModel;
import com.teachermanagement.daniellucas.models.SubjectModel;
import com.teachermanagement.daniellucas.models.TeacherModel;
import com.teachermanagement.daniellucas.repositories.ProjectRepository;
import com.teachermanagement.daniellucas.repositories.StudentRepository;
import com.teachermanagement.daniellucas.repositories.SubjectRepository;
import com.teachermanagement.daniellucas.repositories.TeacherRepository;
import com.teachermanagement.daniellucas.repositories.UserRepository;

@Service
public class ProjectService {
	
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    public List<ProjectDTO> findAll(){
        return projectRepository.findAll().stream().map(x -> new ProjectDTO(x, x.getStudents())).toList();
    }

    public ProjectDTO findById(Long id) {
    	ProjectModel projectModel = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subject"));
        return new ProjectDTO(projectModel, projectModel.getStudents());
    }
    
    public ProjectDTO insertStudentIntoProject(ProjectDTO dto) {
    	ProjectModel entity = new ProjectModel();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        TeacherModel teacher = teacherRepository.findById(dto.getTeacherId()).get();
        entity.setTeacher(teacher);
        entity.getStudents().clear();

        for (StudentDTO i : dto.getStudents()) {
            StudentModel studentModel = studentRepository.findById(i.getId()).get();
            entity.getStudents().add(studentModel);

        }
        projectRepository.save(entity);
        return new ProjectDTO(entity);
    }

    public ProjectDTO update(Long id, ProjectDTO dto) {
        try {
        	ProjectModel entity = projectRepository.findById(id).get();
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            TeacherModel teacher = teacherRepository.findById(dto.getTeacherId()).get();
            entity.setTeacher(teacher);
            projectRepository.save(entity);
            return new ProjectDTO(entity);
        }
        catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException("Subject id: " + id);
        }
    }

    public void delete(Long id) {
        try {
        	projectRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Subject id: " + id);
        }
    }
}
