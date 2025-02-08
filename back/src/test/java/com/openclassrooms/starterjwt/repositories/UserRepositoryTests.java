package com.openclassrooms.starterjwt.repositories;

import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testCreateUser() {
        User user = new User("alice@example.com", "Doe", "Alice", "securePassword123", false);
        userRepository.save(user);

        User newUser = userRepository.findByEmail("alice@example.com").orElse(null);

        assertThat(newUser.getId()).isNotNull();
        assertThat(newUser.getFirstName()).isEqualTo("Alice");
    }

    @Test
    public void testDeleteUser() {
        User user = new User("alice@example.com", "Doe", "Alice", "securePassword123", false);
        userRepository.save(user);

        userRepository.delete(user);

        User newUser = userRepository.findByEmail("alice@example.com").orElse(null);

        assertThat(newUser).isNull();
    }
}
