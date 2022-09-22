package com.teachermanagement.daniellucas.controllers;

import com.teachermanagement.daniellucas.dto.SubjectDTO;
import com.teachermanagement.daniellucas.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/turmas")
public class SubjectController {

    @Autowired
    private SubjectService service;

    @GetMapping
    public ResponseEntity<List<SubjectDTO>> findAllSubjects() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO> findSubjectById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    private ResponseEntity<SubjectDTO> saveSubject(@RequestBody SubjectDTO subjectDTO) {
        subjectDTO = service.insert(subjectDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(subjectDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(subjectDTO);
    }

    @PutMapping("/{id}")
    private ResponseEntity<SubjectDTO> updateSubject(@PathVariable Long id, @RequestBody SubjectDTO subjectDTO) {
        return ResponseEntity.ok().body(service.update(id, subjectDTO));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteSubjectById(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
