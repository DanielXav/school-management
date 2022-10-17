package com.teachermanagement.daniellucas.dto;

import com.teachermanagement.daniellucas.models.TeacherModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {

	private String name;
	private	String graduation;
	private String registration;
	private String email;
	
	public TeacherDTO(TeacherModel entity) {
		name = entity.getName();
		graduation = entity.getGraduation();
		registration = entity.getRegistration();
		email = entity.getEmail();
	}
}
