package com.teachermanagement.daniellucas.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.teachermanagement.daniellucas.models.ProjectModel;
import com.teachermanagement.daniellucas.models.StudentModel;
import com.teachermanagement.daniellucas.models.SubjectModel;

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
	private List<StudentDTO> students = new ArrayList<>();

    public ProjectDTO(ProjectModel projectModel) {
        id = projectModel.getId();
        name = projectModel.getName();
        description = projectModel.getDescription();
        teacherId = projectModel.getTeacher().getId();
    }
    
	public ProjectDTO(ProjectModel projectModel, List<StudentModel> students) {
		this(projectModel);
		students.forEach(x -> {
			this.students.add(new StudentDTO(x, "error"));
		});
	}
}
