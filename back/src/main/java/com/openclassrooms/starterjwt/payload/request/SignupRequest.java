package com.openclassrooms.starterjwt.payload.request;

import javax.validation.constraints.*;

import com.openclassrooms.starterjwt.utils.jacoco.Generated;
import lombok.Data;

@Data
@Generated
public class SignupRequest {
  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(min = 3, max = 20)
  private String firstName;

  @NotBlank
  @Size(min = 3, max = 20)
  private String lastName;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;
}
