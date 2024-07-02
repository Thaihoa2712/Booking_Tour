package com.easy.tour.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class LoginMessage {
    String message;
    Boolean status;

}
