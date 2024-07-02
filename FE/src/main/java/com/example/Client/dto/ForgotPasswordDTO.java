package com.example.Client.dto;

import com.example.Client.validator.NotWhiteSpace;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
public class ForgotPasswordDTO {

    @NotWhiteSpace
    @NotEmpty(message = "You must enter Email field.")
    @Email(message = "You must enter a valid email address.")
    private String email;
}
