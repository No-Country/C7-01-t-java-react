package com.nocountry.findyourpet.controller;

import com.nocountry.findyourpet.models.request.LoginRequest;
import com.nocountry.findyourpet.models.response.LoginResponse;
import com.nocountry.findyourpet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest userLogin){
        return userService.login(userLogin);
    }
}
