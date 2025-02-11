package com.openclassrooms.starterjwt.dto;

import com.openclassrooms.starterjwt.utils.jacoco.Generated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class TeacherDto {
    private Long id;
    @NotBlank
    @Size(max = 20)
    private String lastName;

    @NotBlank
    @Size(max = 20)
    private String firstName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
