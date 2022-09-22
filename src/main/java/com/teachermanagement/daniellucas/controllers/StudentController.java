package com.teachermanagement.daniellucas.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.teachermanagement.daniellucas.dto.StudentDTO;
import com.teachermanagement.daniellucas.services.StudentService;

@RestController
@RequestMapping("/alunos")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> findAllStudents() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> findStudentById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    private ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO) {
    	studentDTO = service.insert(studentDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(studentDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(studentDTO);
    }

    @PutMapping("/{id}")
    private ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok().body(service.update(id, studentDTO));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteStudentById(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
