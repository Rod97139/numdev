package com.openclassrooms.starterjwt.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openclassrooms.starterjwt.utils.jacoco.Generated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class UserDto {
    private Long id;

    @NonNull
    @Size(max = 50)
    @Email
    private String email;

    @NonNull
    @Size(max = 20)
    private String lastName;

    @NonNull
    @Size(max = 20)
    private String firstName;

    @NonNull
    private boolean admin;

    @JsonIgnore
    @Size(max = 120)
    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
