package com.teachermanagement.daniellucas.services;

import com.teachermanagement.daniellucas.dto.TeacherDTO;
import com.teachermanagement.daniellucas.models.TeacherModel;
import com.teachermanagement.daniellucas.repositories.TeacherRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest {

    @Mock
    private TeacherRepository repository;
    @InjectMocks
    private TeacherService serviceUnderTest;
    private long existingId;
    private long nonExistingId;
    private TeacherModel teacherModel;

    @BeforeEach
    void setUp() {
        existingId = 1L;
        nonExistingId = 2L;
        teacherModel = new TeacherModel(1L,"Ramon", "Phd", "2020123", "ramon@gmail.com");
        serviceUnderTest = new TeacherService(repository);
//
//        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(teacherModel));
//        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
//
//        Mockito.when(repository.getOne(existingId)).thenReturn(teacherModel);
    }

    @Test
    void saveTeacher() {
        TeacherModel teacherModel = new TeacherModel(1L, "Ramon", "Phd", "2020123", "ramon@gmail.com");
        TeacherDTO teacherDTO = new TeacherDTO(teacherModel);
        serviceUnderTest.insert(teacherDTO);

        ArgumentCaptor<TeacherModel> teacherModelArgumentCaptor = ArgumentCaptor.forClass(TeacherModel.class);
        Mockito.verify(repository).save(teacherModelArgumentCaptor.capture());
        TeacherModel capturedTeacher = teacherModelArgumentCaptor.getValue();
        Assertions.assertEquals(teacherModel, capturedTeacher);
    }

    @Test
    void shouldFindAllTeachers() {
        serviceUnderTest.findAll();
        Mockito.verify(repository).findAll();
    }

    @Test
    @Disabled
    void shouldFindTeacherById() {
        TeacherModel teacherModel = new TeacherModel("Ramon", "Phd", "2020123", "ramon@gmail.com");
        TeacherDTO teacherDTO = new TeacherDTO(teacherModel);
        serviceUnderTest.findById(2L);
        Mockito.verify(repository).findById(2L);
    }

    @Test
    @Disabled
    public void findByIdShouldReturnProductDTOWhenIdExists() {

        TeacherDTO result = serviceUnderTest.findById(existingId);

        Assertions.assertNotNull(result);

        Mockito.verify(repository, Mockito.times(1)).findById(existingId);
    }
}
