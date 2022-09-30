package com.teachermanagement.daniellucas.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_student")
public class StudentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column(unique = true)
    private String registration;
    @Column(unique = true)
    private String email;
    @ManyToMany(mappedBy = "students")
    Set<SubjectModel> subjects = new HashSet<>();
}
