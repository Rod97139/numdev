package com.openclassrooms.starterjwt.repositories;

import com.openclassrooms.starterjwt.models.Session;
import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.repository.SessionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class SessionRepositoryTests {

    @Autowired
    private SessionRepository sessionRepository;

    @Test
    public void testCreateSession() {
        Teacher teacher = Teacher.builder()
                .firstName("John")
                .lastName("Doe")
                .build();

        Session session = Session.builder()
                .name("Test Session")
                .date(new Date())
                .description("This is a test session")
                .teacher(teacher)
                .users(Collections.emptyList())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        sessionRepository.save(session);

        Session newSession = sessionRepository.findById(session.getId()).orElse(null);

        assertThat(newSession).isNotNull();
        assertThat(newSession.getName()).isEqualTo("Test Session");
        assertThat(newSession.getTeacher().getFirstName()).isEqualTo("John");
        assertThat(newSession.getTeacher().getLastName()).isEqualTo("Doe");
    }

    @Test
    public void testDeleteSession() {
        Teacher teacher = Teacher.builder()
                .firstName("John")
                .lastName("Doe")
                .build();

        Session session = Session.builder()
                .name("Test Session")
                .date(new Date())
                .description("This is a test session")
                .teacher(teacher)
                .users(Collections.emptyList())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        sessionRepository.save(session);

        sessionRepository.delete(session);

        Session newSession = sessionRepository.findById(session.getId()).orElse(null);

        assertThat(newSession).isNull();
    }
}