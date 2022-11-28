package com.teachermanagement.daniellucas.dto;

import com.teachermanagement.daniellucas.models.StudentModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private Long id;
    private String name;
    private String registration;
    private String email;
    private Long projectId;

    public StudentDTO(StudentModel studentModel) {
        id = studentModel.getId();
        name = studentModel.getName();
        registration = studentModel.getRegistration();
        email = studentModel.getEmail();
        projectId = studentModel.getProject().getId();
    }
}
