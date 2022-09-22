package com.teachermanagement.daniellucas.dto;

import javax.persistence.Entity;

import com.teachermanagement.daniellucas.models.SubjectModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {

    private Long id;
    private String name;
    private String room;
    
	public SubjectDTO(SubjectModel entity) {
		id = entity.getId();
		name = entity.getName();
		room = entity.getRoom();
	}
    
    
}
