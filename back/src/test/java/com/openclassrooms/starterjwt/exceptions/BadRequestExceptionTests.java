package com.openclassrooms.starterjwt.exceptions;

import com.openclassrooms.starterjwt.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BadRequestExceptionTests {

    @Test
    void testBadRequestException() {
        assertThatThrownBy(() -> {
            throw new BadRequestException();
        }).isInstanceOf(BadRequestException.class)
                .hasMessage(null)
                .hasNoCause();

        try {
            throw new BadRequestException();
        } catch (BadRequestException ex) {
            ResponseStatusException responseStatusException = new ResponseStatusException(HttpStatus.BAD_REQUEST);
            assertThat(ex.getClass().getAnnotation(ResponseStatus.class).value()).isEqualTo(responseStatusException.getStatus());
        }
    }
}