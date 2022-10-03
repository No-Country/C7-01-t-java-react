package com.nocountry.findyourpet.models.request;

import lombok.Data;

@Data
public class UserRequest {

    private String email;
    private String password;
    private String password2;
    private String name ;
    private String lastName;
    private String phone;
    private String facebookAccount;

}
