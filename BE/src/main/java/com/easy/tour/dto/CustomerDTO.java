package com.easy.tour.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class CustomerDTO {

    private Long id;

    private String userName;

    private String password;

    private boolean enabled;

    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private String confirmPassword;
}
