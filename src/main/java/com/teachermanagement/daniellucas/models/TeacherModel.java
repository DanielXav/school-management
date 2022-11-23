package com.teachermanagement.daniellucas.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_teacher")
public class TeacherModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private	String graduation;
	@Column(unique = true)
	private String registration;
	@Column(unique = true)
	private String email;
	@OneToMany(mappedBy = "teacher")
	private List<SubjectModel> subjects = new ArrayList<>();
	@OneToOne(mappedBy = "teacher")
	private ProjectModel project;
	
}
