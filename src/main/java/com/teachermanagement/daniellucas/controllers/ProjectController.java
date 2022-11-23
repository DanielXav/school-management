package com.teachermanagement.daniellucas.controllers;

import com.teachermanagement.daniellucas.dto.ProjectDTO;
import com.teachermanagement.daniellucas.dto.StudentDTO;
import com.teachermanagement.daniellucas.services.ProjectService;
import com.teachermanagement.daniellucas.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/projetos")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> findAllStudents() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> findStudentById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    private ResponseEntity<ProjectDTO> saveStudent(@RequestBody ProjectDTO projectDTO) {
        projectDTO = service.insert(projectDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(projectDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(projectDTO);
    }

//    @PutMapping("/{id}")
//    private ResponseEntity<ProjectDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO projectDTO) {
//        return ResponseEntity.ok().body(service.update(id, projectDTO));
//    }

//    @DeleteMapping("/{id}")
//    private ResponseEntity<Void> deleteStudentById(@PathVariable Long id) {
//        service.delete(id);
//        return ResponseEntity.noContent().build();
//    }
}
