package com.openclassrooms.starterjwt.security.jwt;

import com.openclassrooms.starterjwt.security.services.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AuthTokenFilterTests {

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private AuthTokenFilter authTokenFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoFilterInternal_withValidJwt() throws Exception {
        String token = "valid.jwt.token";
        String username = "testuser";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtUtils.validateJwtToken(token)).thenReturn(true);
        when(jwtUtils.getUserNameFromJwtToken(token)).thenReturn(username);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(userDetails.getAuthorities()).thenReturn(null);

        authTokenFilter.doFilterInternal(request, response, filterChain);

        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken)
                SecurityContextHolder.getContext().getAuthentication();

        assertThat(authentication).isNotNull();
        assertThat(authentication.getPrincipal()).isEqualTo(userDetails);

        verify(filterChain).doFilter(request, response);
    }

//    @Test
//    void testDoFilterInternal_withInvalidJwt() throws Exception {
//        String token = "invalid.jwt.token";
//
//        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
//        when(jwtUtils.validateJwtToken(token)).thenReturn(false);
//
//        authTokenFilter.doFilterInternal(request, response, filterChain);
//
//        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
//        verify(filterChain).doFilter(request, response);
//    }

    @Test
    void testDoFilterInternal_withoutJwt() throws Exception {
        when(request.getHeader("Authorization")).thenReturn(null);

        authTokenFilter.doFilterInternal(request, response, filterChain);

        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
        verify(filterChain).doFilter(request, response);
    }
//
//    @Test
//    void testDoFilterInternal_withException() throws Exception {
//
//        UsernamePasswordAuthenticationToken authentication = null;
//        String token = "valid.jwt.token";
//
//        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
//        when(jwtUtils.validateJwtToken(token)).thenThrow(new RuntimeException("JWT validation error")); // Force exception
//
//        authTokenFilter.doFilterInternal(request, response, filterChain);
//
//        // Ensure no authentication is set due to the exception
//        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
//        verify(filterChain).doFilter(request, response);
//    }
}
