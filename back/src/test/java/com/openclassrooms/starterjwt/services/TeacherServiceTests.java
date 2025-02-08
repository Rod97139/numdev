package com.openclassrooms.starterjwt.services;

import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TeacherServiceTests {
        @Mock
        private TeacherRepository teacherRepository;

        @InjectMocks
        private TeacherService teacherService;

        private Teacher teacher1;
        private Teacher teacher2;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);

            teacher1 = new Teacher();
            teacher1.setId(1L);
            teacher1.setFirstName("John");
            teacher1.setLastName("Doe");

            teacher2 = new Teacher();
            teacher2.setId(2L);
            teacher2.setFirstName("Jane");
            teacher2.setLastName("Smith");
        }

        @Test
        void testFindAllTeachers() {
            List<Teacher> teachers = Arrays.asList(teacher1, teacher2);
            when(teacherRepository.findAll()).thenReturn(teachers);

            List<Teacher> result = teacherService.findAll();

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals("John", result.get(0).getFirstName());
            assertEquals("Jane", result.get(1).getFirstName());

            verify(teacherRepository, times(1)).findAll();
        }

        @Test
        void testFindById_Success() {
            when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher1));

            Teacher result = teacherService.findById(1L);

            assertNotNull(result);
            assertEquals(1L, result.getId());
            assertEquals("John", result.getFirstName());

            verify(teacherRepository, times(1)).findById(1L);
        }

        @Test
        void testFindById_NotFound() {
            when(teacherRepository.findById(3L)).thenReturn(Optional.empty());

            Teacher result = teacherService.findById(3L);

            assertNull(result);
            verify(teacherRepository, times(1)).findById(3L);
        }
    }
