package com.teachermanagement.daniellucas.repositories;

import com.teachermanagement.daniellucas.models.TeacherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherModel, Long> {
}
