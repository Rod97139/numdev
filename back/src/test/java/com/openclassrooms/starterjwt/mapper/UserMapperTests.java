package com.openclassrooms.starterjwt.mapper;

import com.openclassrooms.starterjwt.dto.UserDto;
import com.openclassrooms.starterjwt.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMapperTests {

    private UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        userMapper = Mappers.getMapper(UserMapper.class);
    }

    @Test
    public void testToEntity() {
        UserDto userDto = createUserDto(1L, "user@example.com", "Doe", "John", true);

        User user = userMapper.toEntity(userDto);

        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getLastName(), user.getLastName());
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(userDto.getPassword(), user.getPassword());
        assertEquals(userDto.isAdmin(), user.isAdmin());
    }

    @Test
    public void testToDto() {
        User user = createUser(1L, "user@example.com", "Doe", "John", true);

        UserDto userDto = userMapper.toDto(user);

        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getPassword(), userDto.getPassword());
        assertEquals(user.isAdmin(), userDto.isAdmin());
    }

    @Test
    public void testToEntityList() {
        UserDto userDto1 = createUserDto(1L, "user1@example.com", "Doe", "John", true);
        UserDto userDto2 = createUserDto(2L, "user2@example.com", "Smith", "Jane", false);

        List<User> users = userMapper.toEntity(Arrays.asList(userDto1, userDto2));

        assertEquals(2, users.size());
        assertEquals("user1@example.com", users.get(0).getEmail());
        assertEquals("user2@example.com", users.get(1).getEmail());
    }

    @Test
    public void testToDtoList() {
        User user1 = createUser(1L, "user1@example.com", "Doe", "John", true);
        User user2 = createUser(2L, "user2@example.com", "Smith", "Jane", false);

        List<UserDto> userDtos = userMapper.toDto(Arrays.asList(user1, user2));

        assertEquals(2, userDtos.size());
        assertEquals("user1@example.com", userDtos.get(0).getEmail());
        assertEquals("user2@example.com", userDtos.get(1).getEmail());
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

    private UserDto createUserDto(Long id, String email, String lastName, String firstName, boolean isAdmin) {
        return new UserDto(
                id,
                email,
                lastName,
                firstName,
                isAdmin,
                "password",  // The password will be ignored in serialization but can still be set for testing.
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
