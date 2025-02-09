package com.openclassrooms.starterjwt.security.jwt;

import com.openclassrooms.starterjwt.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import java.lang.reflect.Field;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JwtUtilsTests {

    @Mock
    private Authentication authentication;

    @Mock
    private UserDetailsImpl userDetails;

    private JwtUtils jwtUtils;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        jwtUtils = new JwtUtils();
        setField(jwtUtils, "jwtSecret", "testSecret");
        setField(jwtUtils, "jwtExpirationMs", 3600000); // 1 hour
    }

    private void setField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    @Test
    public void testGenerateJwtToken() {
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("testuser");

        String token = jwtUtils.generateJwtToken(authentication);

        assertNotNull(token);
        assertTrue(jwtUtils.validateJwtToken(token));
        assertEquals("testuser", jwtUtils.getUserNameFromJwtToken(token));
    }

    @Test
    public void testGetUserNameFromJwtToken() {
        String token = Jwts.builder()
                .setSubject("testuser")
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 3600000))
                .signWith(SignatureAlgorithm.HS512, "testSecret")
                .compact();

        String username = jwtUtils.getUserNameFromJwtToken(token);

        assertEquals("testuser", username);
    }

    @Test
    public void testValidateJwtToken_ValidToken() {
        String token = Jwts.builder()
                .setSubject("testuser")
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 3600000))
                .signWith(SignatureAlgorithm.HS512, "testSecret")
                .compact();

        assertTrue(jwtUtils.validateJwtToken(token));
    }

    @Test
    public void testValidateJwtToken_InvalidToken() {
        String token = "invalidToken";

        assertFalse(jwtUtils.validateJwtToken(token));
    }

    @Test
    public void testValidateJwtToken_ExpiredToken() {
        String token = Jwts.builder()
                .setSubject("testuser")
                .setIssuedAt(new Date(System.currentTimeMillis() - 10000))
                .setExpiration(new Date(System.currentTimeMillis() - 5000))
                .signWith(SignatureAlgorithm.HS512, "testSecret")
                .compact();

        assertFalse(jwtUtils.validateJwtToken(token));
    }
}