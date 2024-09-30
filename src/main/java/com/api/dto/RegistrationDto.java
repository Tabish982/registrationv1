package com.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegistrationDto {

    @Size(min = 2, message = "Min should be 2 letters")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 10, max = 10, message = "Should be 10 digits")
    private String mobile;

}
