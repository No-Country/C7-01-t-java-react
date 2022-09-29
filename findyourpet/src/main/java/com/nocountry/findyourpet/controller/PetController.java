package com.nocountry.findyourpet.controller;


import com.nocountry.findyourpet.exceptions.MyException;
import com.nocountry.findyourpet.models.request.PetRequest;
import com.nocountry.findyourpet.models.response.PetResponse;
import com.nocountry.findyourpet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PetController {
    @Autowired
    private PetService petService;
    //endpoint para registro, modificacion

    @PostMapping("/pet/register")
    public ResponseEntity<PetResponse> registerPet(@RequestBody PetRequest petRequest , @PathVariable Long idUser){
        PetResponse responsePet = new PetResponse(); //ver si usar response o no
        try {
            petService.register(petRequest, idUser);
            return ResponseEntity.ok(responsePet);
        } catch (MyException e) {
            e.getMessage();
            return null;
        }
    }

}
