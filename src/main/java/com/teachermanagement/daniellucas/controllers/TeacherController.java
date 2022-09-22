package com.teachermanagement.daniellucas.controllers;

import com.teachermanagement.daniellucas.dto.TeacherDTO;
import com.teachermanagement.daniellucas.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService service;

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> findAllTeachers() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> findTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    private ResponseEntity<TeacherDTO> saveTeacher(@RequestBody TeacherDTO teacherDTO) {
        teacherDTO = service.insert(teacherDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(teacherDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(teacherDTO);
    }

    @PutMapping("/{id}")
    private ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Long id, @RequestBody TeacherDTO teacherDTO) {
        return ResponseEntity.ok().body(service.update(id, teacherDTO));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteTeacherById(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
