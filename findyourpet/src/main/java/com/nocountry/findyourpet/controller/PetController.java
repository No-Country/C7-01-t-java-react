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
public class PetController {
    @Autowired
    private PetService petService;

    @GetMapping("/all")
    public ResponseEntity<List<PetResponse>> getAll(){
        return ResponseEntity.ok(petService.getAll());
    }

    @PostMapping("/{idUser}/register")
    public ResponseEntity<PetResponse> registerPet(@RequestBody PetRequest petRequest ,@PathVariable Long idUser){
        try {
            PetResponse response = petService.register(petRequest, idUser);
            return ResponseEntity.ok(response);
        } catch (MyException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<PetResponse> modifyPet(@PathVariable Long petId, @RequestBody PetRequest petRequest){
        try {
            PetResponse response = petService.modify(petRequest,petId);
            return ResponseEntity.ok(response);
        } catch (MyException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}
