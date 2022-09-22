package com.teachermanagement.daniellucas.dto;

import javax.persistence.Entity;
import javax.security.auth.Subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {

    private Long id;
    private String name;
    private String room;
    
	public SubjectDTO(Subject entity) {
		id = entity.getId();
		name = entity.getName();
		room = entity.getRoom();
	}
    
    
}
