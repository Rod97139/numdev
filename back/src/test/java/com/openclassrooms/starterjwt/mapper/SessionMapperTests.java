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
import java.util.Collections;
import java.util.List;

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

        User user1 = createUser(1L, "user1@example.com", "Doe", "John", true);
        User user2 = createUser(2L, "user2@example.com", "Smith", "Jane", false);

        when(teacherService.findById(1L)).thenReturn(new Teacher());
        when(userService.findById(1L)).thenReturn(user1);
        when(userService.findById(2L)).thenReturn(user2);

        Session session = sessionMapper.toEntity(sessionDto);

        assertEquals("Test Description", session.getDescription());
        assertEquals(2, session.getUsers().size());
    }

    @Test
    public void testToDto() {
        User user1 = createUser(1L, "user1@example.com", "Doe", "John", true);
        User user2 = createUser(2L, "user2@example.com", "Smith", "Jane", false);

        Teacher teacher = new Teacher();
        teacher.setId(1L);

        Session session = new Session();
        session.setDescription("Test Description");
        session.setTeacher(teacher);
        session.setUsers(Arrays.asList(user1, user2));

        SessionDto sessionDto = sessionMapper.toDto(session);

        assertEquals("Test Description", sessionDto.getDescription());
        assertEquals(1L, sessionDto.getTeacher_id());
        assertEquals(2, sessionDto.getUsers().size());
    }

    @Test
    public void testToEntityList() {
        SessionDto sessionDto1 = new SessionDto();
        sessionDto1.setDescription("Session 1");
        sessionDto1.setTeacher_id(1L);
        sessionDto1.setUsers(Arrays.asList(1L));

        SessionDto sessionDto2 = new SessionDto();
        sessionDto2.setDescription("Session 2");
        sessionDto2.setTeacher_id(2L);
        sessionDto2.setUsers(Arrays.asList(2L));

        when(teacherService.findById(1L)).thenReturn(new Teacher());
        when(teacherService.findById(2L)).thenReturn(new Teacher());

        User user1 = createUser(1L, "user1@example.com", "Doe", "John", true);
        User user2 = createUser(2L, "user2@example.com", "Smith", "Jane", false);

        when(userService.findById(1L)).thenReturn(user1);
        when(userService.findById(2L)).thenReturn(user2);

        List<Session> sessions = sessionMapper.toEntity(Arrays.asList(sessionDto1, sessionDto2));

        assertEquals(2, sessions.size());
        assertEquals("Session 1", sessions.get(0).getDescription());
        assertEquals("Session 2", sessions.get(1).getDescription());
    }

    @Test
    public void testToDtoList() {
        User user1 = createUser(1L, "user1@example.com", "Doe", "John", true);
        User user2 = createUser(2L, "user2@example.com", "Smith", "Jane", false);

        Teacher teacher1 = new Teacher();
        teacher1.setId(1L);

        Teacher teacher2 = new Teacher();
        teacher2.setId(2L);

        Session session1 = new Session();
        session1.setDescription("Session 1");
        session1.setTeacher(teacher1);
        session1.setUsers(Collections.singletonList(user1));

        Session session2 = new Session();
        session2.setDescription("Session 2");
        session2.setTeacher(teacher2);
        session2.setUsers(Collections.singletonList(user2));

        List<SessionDto> sessionDtos = sessionMapper.toDto(Arrays.asList(session1, session2));

        assertEquals(2, sessionDtos.size());
        assertEquals("Session 1", sessionDtos.get(0).getDescription());
        assertEquals(1L, sessionDtos.get(0).getTeacher_id());
        assertEquals(1, sessionDtos.get(0).getUsers().size());

        assertEquals("Session 2", sessionDtos.get(1).getDescription());
        assertEquals(2L, sessionDtos.get(1).getTeacher_id());
        assertEquals(1, sessionDtos.get(1).getUsers().size());
    }

    private User createUser(Long id, String email, String lastName, String firstName, boolean isAdmin) {
        return User.builder()
                .id(id)
                .email(email)
                .lastName(lastName)
                .firstName(firstName)
                .password("password")
                .admin(isAdmin)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
