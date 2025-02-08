package com.openclassrooms.starterjwt.security.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDetailsImplTests {

    private UserDetailsImpl userDetails;

    @BeforeEach
    void setUp() {
        userDetails = UserDetailsImpl.builder()
                .id(1L)
                .username("testuser")
                .firstName("John")
                .lastName("Doe")
                .admin(true)
                .password("password123")
                .build();
    }

    @Test
    void testGetAuthorities() {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        assertThat(authorities).isInstanceOf(HashSet.class);
        assertThat(authorities).isEmpty();
    }

    @Test
    void testIsAccountNonExpired() {
        assertThat(userDetails.isAccountNonExpired()).isTrue();
    }

    @Test
    void testIsAccountNonLocked() {
        assertThat(userDetails.isAccountNonLocked()).isTrue();
    }

    @Test
    void testIsCredentialsNonExpired() {
        assertThat(userDetails.isCredentialsNonExpired()).isTrue();
    }

    @Test
    void testIsEnabled() {
        assertThat(userDetails.isEnabled()).isTrue();
    }

    @Test
    void testEquals() {
        UserDetailsImpl anotherUserDetails = UserDetailsImpl.builder()
                .id(1L)
                .username("anotheruser")
                .firstName("Jane")
                .lastName("Doe")
                .admin(false)
                .password("password456")
                .build();

        assertThat(userDetails).isEqualTo(anotherUserDetails);
    }

    @Test
    void testNotEquals() {
        UserDetailsImpl anotherUserDetails = UserDetailsImpl.builder()
                .id(2L)
                .username("anotheruser")
                .firstName("Jane")
                .lastName("Doe")
                .admin(false)
                .password("password456")
                .build();

        assertThat(userDetails).isNotEqualTo(anotherUserDetails);
    }
}