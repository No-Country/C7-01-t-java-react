package com.nocountry.findyourpet.controller;


import com.nocountry.findyourpet.exceptions.MyException;
import com.nocountry.findyourpet.models.request.PetRequest;
import com.nocountry.findyourpet.models.response.PetResponse;
import com.nocountry.findyourpet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
@CrossOrigin(origins = "http://localhost:3000")
public class PetController {
    @Autowired
    private PetService petService;

    @GetMapping("/all")
    public ResponseEntity<List<PetResponse>> getAll(){
        return ResponseEntity.ok(petService.getAll());
    }

    @PostMapping("/{idUser}/register")
    public ResponseEntity<?> registerPet(@RequestBody PetRequest petRequest ,@PathVariable Long idUser){
        try {
            return petService.register(petRequest,idUser);
        } catch (MyException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyPet(@PathVariable Long id, @RequestBody PetRequest petRequest){
        try {
            return petService.modify(petRequest , id);
        } catch (MyException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}
