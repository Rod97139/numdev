package com.openclassrooms.starterjwt.mapper;

import com.openclassrooms.starterjwt.dto.SessionDto;
import com.openclassrooms.starterjwt.models.Session;
import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.services.TeacherService;
import com.openclassrooms.starterjwt.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SessionMapperTests {

    @Mock
    private TeacherService teacherService;

    @Mock
    private UserService userService;

    @InjectMocks
    private SessionMapper sessionMapper = Mappers.getMapper(SessionMapper.class);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testToEntity() {
        SessionDto sessionDto = new SessionDto();
        sessionDto.setDescription("Test Description");
        sessionDto.setTeacher_id(1L);
        sessionDto.setUsers(Arrays.asList(1L, 2L));

        User user1 = User.builder()
                .id(1L)
                .email("user1@example.com")
                .lastName("Doe")
                .firstName("John")
                .password("password1")
                .admin(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        User user2 = User.builder()
                .id(2L)
                .email("user2@example.com")
                .lastName("Smith")
                .firstName("Jane")
                .password("password2")
                .admin(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(teacherService.findById(1L)).thenReturn(new Teacher());
        when(userService.findById(1L)).thenReturn(user1);
        when(userService.findById(2L)).thenReturn(user2);

        Session session = sessionMapper.toEntity(sessionDto);

        assertEquals("Test Description", session.getDescription());
        assertEquals(2, session.getUsers().size());
    }

    @Test
    public void testToDto() {
        User user1 = User.builder()
                .id(1L)
                .email("user1@example.com")
                .lastName("Doe")
                .firstName("John")
                .password("password1")
                .admin(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        User user2 = User.builder()
                .id(2L)
                .email("user2@example.com")
                .lastName("Smith")
                .firstName("Jane")
                .password("password2")
                .admin(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Session session = new Session();
        session.setDescription("Test Description");
        session.setTeacher(new Teacher());
        session.setUsers(Arrays.asList(user1, user2));

        SessionDto sessionDto = sessionMapper.toDto(session);

        assertEquals("Test Description", sessionDto.getDescription());
        assertEquals(2, sessionDto.getUsers().size());
    }
}