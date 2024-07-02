package com.easy.tour.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.util.Set;


@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
public class UserDTO extends BaseObject {
    private Long userId;

    private String uuid;

    @Length(min = 2, max = 15, message = "Miss!!!")
    private String firstName;

    @Length(min = 2, max = 15, message = "Miss!!!")
    private String lastName;

    @Email(message = "Please!!!Try again")
    private String email;


    private Long phoneNumber;

    @NotEmpty(message = "Miss!!!")
    private String address;

    private Boolean gender;

    private String imagePath;

    private String password;

    private Set<String> roles;

    private String accessToken;
}
