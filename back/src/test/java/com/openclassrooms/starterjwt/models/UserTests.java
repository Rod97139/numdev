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

public class UserTests {

    private Validator validator;
    private User user;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        user = User.builder()
                .email("test@example.com")
                .firstName("John")
                .lastName("Doe")
                .password("password123")
                .admin(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Test
    void testValidUser() {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations).isEmpty();
    }

    @Test
    void testEmailSize() {
        user.setEmail(new String(new char[51]).replace('\0', 'A') + "@example.com");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testEmailFormat() {
        user.setEmail("invalid-email");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testLastNameSize() {
        user.setLastName(new String(new char[21]).replace('\0', 'A'));
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testFirstNameSize() {
        user.setFirstName(new String(new char[21]).replace('\0', 'A'));
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testPasswordSize() {
        user.setPassword(new String(new char[121]).replace('\0', 'A'));
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations).isNotEmpty();
    }
}