package com.openclassrooms.starterjwt;

import com.openclassrooms.starterjwt.controllers.AuthController;
import com.openclassrooms.starterjwt.security.jwt.JwtUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class SpringBootSecurityJwtApplicationTests {

	@Autowired
	private AuthController authController;

	@Autowired
	private JwtUtils jwtUtils;

	@Test
	@DisplayName("Test 1:authController context is loaded")
	void contextLoads() {
		assertThat(authController).isNotNull();
	}

}
