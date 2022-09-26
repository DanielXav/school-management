package com.teachermanagement.daniellucas.dto;

import javax.persistence.Entity;

import com.teachermanagement.daniellucas.models.StudentModel;
import com.teachermanagement.daniellucas.models.SubjectModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {

    private Long id;
    private String name;
    private String room;
    private Long teacherId;
//	private Long studentId;
	private List<StudentDTO> students = new ArrayList<>();
    
	public SubjectDTO(SubjectModel entity) {
		id = entity.getId();
		name = entity.getName();
		room = entity.getRoom();
		teacherId = entity.getTeacher().getId();
	}

	public SubjectDTO(SubjectModel entity, Set<StudentModel> students) {
		this(entity);
		students.forEach(x -> {
			this.students.add(new StudentDTO(x));
		});
	}
}
