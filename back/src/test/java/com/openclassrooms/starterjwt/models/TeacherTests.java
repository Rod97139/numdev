package com.openclassrooms.starterjwt.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class TeacherTests {

    private Validator validator;
    private Teacher teacher;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        teacher = Teacher.builder()
                .firstName("John")
                .lastName("Doe")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Test
    void testValidTeacher() {
        Set<ConstraintViolation<Teacher>> violations = validator.validate(teacher);
        assertThat(violations).isEmpty();
    }

    @Test
    void testLastNameNotBlank() {
        teacher.setLastName("");
        Set<ConstraintViolation<Teacher>> violations = validator.validate(teacher);
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testLastNameSize() {
        teacher.setLastName(new String(new char[21]).replace('\0', 'A'));
        Set<ConstraintViolation<Teacher>> violations = validator.validate(teacher);
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testFirstNameNotBlank() {
        teacher.setFirstName("");
        Set<ConstraintViolation<Teacher>> violations = validator.validate(teacher);
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testFirstNameSize() {
        teacher.setFirstName(new String(new char[21]).replace('\0', 'A'));
        Set<ConstraintViolation<Teacher>> violations = validator.validate(teacher);
        assertThat(violations).isNotEmpty();
    }
}