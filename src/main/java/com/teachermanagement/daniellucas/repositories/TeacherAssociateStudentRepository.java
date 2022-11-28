package com.teachermanagement.daniellucas.repositories;

import com.teachermanagement.daniellucas.models.TeacherAssociateStudentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherAssociateStudentRepository extends JpaRepository<TeacherAssociateStudentModel, Long> {
}
