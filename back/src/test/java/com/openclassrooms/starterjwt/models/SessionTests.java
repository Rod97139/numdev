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
                .id(1L)
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

    // New tests for equals, hashCode, canEqual, and timestamps

    @Test
    void testEqualsAndHashCode() {
        Session session1 = Session.builder()
                .id(1L)
                .name("Test Session")
                .date(new Date())
                .description("This is a test session")
                .build();

        Session session2 = Session.builder()
                .id(1L)
                .name("Test Session")
                .date(new Date())
                .description("This is a test session")
                .build();

        Session session3 = Session.builder()
                .id(2L)
                .name("Another Session")
                .date(new Date())
                .description("Different description")
                .build();

        assertThat(session1).isEqualTo(session2);
        assertThat(session1.hashCode()).isEqualTo(session2.hashCode());

        assertThat(session1).isNotEqualTo(session3);
        assertThat(session1.hashCode()).isNotEqualTo(session3.hashCode());
    }

    @Test
    void testCanEqual() {
        Session session1 = Session.builder()
                .id(1L)
                .name("Test Session")
                .date(new Date())
                .description("This is a test session")
                .build();

        assertThat(session1.canEqual(session)).isTrue();
        assertThat(session1.canEqual(new Object())).isFalse();
    }

    @Test
    void testSetCreatedAt() {
        LocalDateTime now = LocalDateTime.now();
        session.setCreatedAt(now);
        assertThat(session.getCreatedAt()).isEqualTo(now);
    }

    @Test
    void testSetUpdatedAt() {
        LocalDateTime now = LocalDateTime.now();
        session.setUpdatedAt(now);
        assertThat(session.getUpdatedAt()).isEqualTo(now);
    }
}
