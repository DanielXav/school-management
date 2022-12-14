package com.teachermanagement.daniellucas.services;

import com.teachermanagement.daniellucas.dto.ProjectDTO;
import com.teachermanagement.daniellucas.dto.StudentDTO;
import com.teachermanagement.daniellucas.dto.TeacherAssociateStudentDTO;
import com.teachermanagement.daniellucas.exceptions.SameStudentAtProjectException;
import com.teachermanagement.daniellucas.models.ProjectModel;
import com.teachermanagement.daniellucas.models.StudentModel;
import com.teachermanagement.daniellucas.models.TeacherAssociateStudentModel;
import com.teachermanagement.daniellucas.models.TeacherModel;
import com.teachermanagement.daniellucas.repositories.ProjectRepository;
import com.teachermanagement.daniellucas.repositories.StudentRepository;
import com.teachermanagement.daniellucas.repositories.TeacherAssociateStudentRepository;
import com.teachermanagement.daniellucas.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherAssociateStudentService {

    @Autowired
    private TeacherAssociateStudentRepository teacherAssociateStudentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private StudentRepository studentRepository;

    public TeacherAssociateStudentDTO insertStudentIntoProject(TeacherAssociateStudentDTO dto) throws Exception {
        TeacherAssociateStudentModel entity = new TeacherAssociateStudentModel();

        TeacherModel teacher = teacherRepository.findById(dto.getTeacherId()).get();
        StudentModel student = studentRepository.findById(dto.getStudentId()).get();
        ProjectModel project = projectRepository.findById(dto.getProjectId()).get();

        for (TeacherAssociateStudentModel i : teacherAssociateStudentRepository.findAll()) {

            if (i.getStudent().getId().equals(student.getId()) || i.getTeacher().getId().equals(teacher.getId()) || i.getProject().getId().equals(project.getId())) {
                throw new SameStudentAtProjectException("Mesmo aluno e professor e projeto");
            }
        }
        
        entity.setTeacher(teacher);
        entity.setStudent(student);
        entity.setProject(project);
        
        student.setFunction(dto.getFunction());
        
        studentRepository.save(student);

        teacherAssociateStudentRepository.save(entity);
        return new TeacherAssociateStudentDTO(entity);
    }
}
