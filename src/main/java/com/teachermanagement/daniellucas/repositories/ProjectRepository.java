package com.teachermanagement.daniellucas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teachermanagement.daniellucas.models.ProjectModel;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectModel, Long> {
}
