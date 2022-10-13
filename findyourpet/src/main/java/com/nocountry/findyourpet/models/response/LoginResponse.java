package com.nocountry.findyourpet.models.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private Boolean ok;
    private String email;
    private String name;

}
