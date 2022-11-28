package com.teachermanagement.daniellucas.controllers;

import com.teachermanagement.daniellucas.dto.ProjectDTO;
import com.teachermanagement.daniellucas.dto.TeacherAssociateStudentDTO;
import com.teachermanagement.daniellucas.services.TeacherAssociateStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/associar-projeto")
public class TeacherAssociateStudentController {

    @Autowired
    private TeacherAssociateStudentService service;

    @PostMapping
    private ResponseEntity<TeacherAssociateStudentDTO> associateStudent(@RequestBody TeacherAssociateStudentDTO teacherAssociateStudentDTO) {
        teacherAssociateStudentDTO = service.insertStudentIntoProject(teacherAssociateStudentDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(teacherAssociateStudentDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(teacherAssociateStudentDTO);
    }
}
