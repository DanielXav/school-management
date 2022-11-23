package com.teachermanagement.daniellucas.dto;

import com.teachermanagement.daniellucas.models.ProjectModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

    private Long id;
    private String name;
    private String description;
    private Long teacherId;

    public ProjectDTO(ProjectModel projectModel) {
        id = projectModel.getId();
        name = projectModel.getName();
        description = projectModel.getDescription();
        teacherId = projectModel.getTeacher().getId();
    }
}
