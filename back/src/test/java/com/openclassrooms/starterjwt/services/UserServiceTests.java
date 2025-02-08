package com.openclassrooms.starterjwt.services;

import com.openclassrooms.starterjwt.controllers.AuthController;
import com.openclassrooms.starterjwt.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@DataJpaTest
@WebMvcTest(AuthController.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User(
                "user@example.com",     // username
                "password123",          // password
                true,                   // enabled
                true,                   // accountNonExpired
                true,                   // credentialsNonExpired
                true,                   // accountNonLocked
                null                    // authorities (null pour simplifier ici)
        );
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        userService.delete(1L);

        // Vérifie que deleteById a été appelé une seule fois avec l'ID correct
        verify(userRepository, times(1)).deleteById(1L);
    }

//    @Test
//    void testFindById_Success() {
//        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//
//        User result = userService.findById(1L);
//
//        assertNotNull(result);
//        assertEquals("user@example.com", result.getUsername());
//
//        verify(userRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    void testFindById_NotFound() {
//        when(userRepository.findById(2L)).thenReturn(Optional.empty());
//
//        User result = userService.findById(2L);
//
//        assertNull(result);
//        verify(userRepository, times(1)).findById(2L);
//    }
//
}
