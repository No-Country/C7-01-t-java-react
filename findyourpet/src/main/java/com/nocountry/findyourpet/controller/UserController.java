package com.nocountry.findyourpet.controller;

import com.nocountry.findyourpet.exceptions.MyException;
import com.nocountry.findyourpet.models.request.UserRequest;
import com.nocountry.findyourpet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    //para realizar el login le tengo que entregar un jwt token a cada usuario
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequest request) {
        try {
            return userService.register(request);
        } catch (MyException e) {
            return new ResponseEntity<>("error in server", HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modify(@PathVariable Long id, @RequestBody UserRequest request) {
        try {
            return userService.modify(request, id);
        } catch (MyException e) {
            return null;
        }
    }
}