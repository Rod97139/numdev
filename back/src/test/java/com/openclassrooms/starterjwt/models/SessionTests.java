package com.openclassrooms.starterjwt.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionTests {

    private Validator validator;
    private Session session;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        session = Session.builder()
                .name("Test Session")
                .date(new Date())
                .description("This is a test session")
                .teacher(new Teacher())
                .users(Collections.emptyList())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Test
    void testValidSession() {
        Set<ConstraintViolation<Session>> violations = validator.validate(session);
        assertThat(violations).isEmpty();
    }

    @Test
    void testNameNotBlank() {
        session.setName("");
        Set<ConstraintViolation<Session>> violations = validator.validate(session);
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testNameSize() {
        session.setName(new String(new char[51]).replace('\0', 'A'));
        Set<ConstraintViolation<Session>> violations = validator.validate(session);
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testDateNotNull() {
        session.setDate(null);
        Set<ConstraintViolation<Session>> violations = validator.validate(session);
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testDescriptionNotNull() {
        session.setDescription(null);
        Set<ConstraintViolation<Session>> violations = validator.validate(session);
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testDescriptionSize() {
        session.setDescription(new String(new char[2501]).replace('\0', 'A'));
        Set<ConstraintViolation<Session>> violations = validator.validate(session);
        assertThat(violations).isNotEmpty();
    }
}