package com.openclassrooms.starterjwt.exceptions;

import com.openclassrooms.starterjwt.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NotFoundExceptionTests {

    @Test
    void testNotFoundException() {
        assertThatThrownBy(() -> {
            throw new NotFoundException();
        }).isInstanceOf(NotFoundException.class)
                .hasMessage(null)
                .hasNoCause();

        try {
            throw new NotFoundException();
        } catch (NotFoundException ex) {
            ResponseStatusException responseStatusException = new ResponseStatusException(HttpStatus.NOT_FOUND);
            assertThat(ex.getClass().getAnnotation(ResponseStatus.class).value()).isEqualTo(responseStatusException.getStatus());
        }
    }
}