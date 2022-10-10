package com.nocountry.findyourpet.controller;

import com.nocountry.findyourpet.exceptions.MyException;
import com.nocountry.findyourpet.models.request.UserRequest;
import com.nocountry.findyourpet.models.response.UserResponse;
import com.nocountry.findyourpet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request){
        //para realizar el registro le tengo que entregar un jwt token a cada usuario
        try {
            UserResponse response = userService.register(request);
            return ResponseEntity.ok(response);
        } catch (MyException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }






}
