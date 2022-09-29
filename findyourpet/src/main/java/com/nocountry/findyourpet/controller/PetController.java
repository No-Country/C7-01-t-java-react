package com.nocountry.findyourpet.controller;


import com.nocountry.findyourpet.exceptions.MyException;
import com.nocountry.findyourpet.models.entity.PetEntity;
import com.nocountry.findyourpet.models.request.PetRequest;
import com.nocountry.findyourpet.models.response.PetResponse;
import com.nocountry.findyourpet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetController {
    @Autowired
    private PetService petService;

    @GetMapping("/pet/all")
    public ResponseEntity<List<PetResponse>> getAll(){
        return ResponseEntity.ok(petService.getAll());
    }

    @PostMapping("/{idUser}/pet/register")
    public ResponseEntity<PetResponse> registerPet(@RequestBody PetRequest petRequest ,@PathVariable Long idUser){
        PetResponse response = new PetResponse();
        try {
            PetEntity pe = petService.register(petRequest, idUser);
            response.setName(pe.getName());
            response.setPhoto(pe.getPhoto());
            response.setAge(pe.getAge());
            response.setDescription(pe.getDescription());
            response.setColor(pe.getColor());
            response.setLocation(pe.getLocation());
            response.setSpecies(pe.getSpecies());
            response.setSex(pe.getSex());
            response.setSize(pe.getSize());
            response.setDate(pe.getDate());
            response.setTail(pe.getTail());
            response.setEars(pe.getEars());
            return ResponseEntity.ok(response);
        } catch (MyException e) {
            e.getMessage();
            return null;
        }
    }

    @PutMapping("/pet/modify/{id}")
    public ResponseEntity<PetResponse> modifyPet(@PathVariable Long petId, @RequestBody PetRequest petRequest){

        PetResponse response = new PetResponse();
        try {
            PetEntity pe = petService.modify(petRequest,petId);
            response.setName(pe.getName());
            response.setPhoto(pe.getPhoto());
            response.setAge(pe.getAge());
            response.setDescription(pe.getDescription());
            response.setColor(pe.getColor());
            response.setLocation(pe.getLocation());
            response.setSpecies(pe.getSpecies());
            response.setSex(pe.getSex());
            response.setSize(pe.getSize());
            response.setDate(pe.getDate());
            response.setTail(pe.getTail());
            response.setEars(pe.getEars());
            return ResponseEntity.ok(response);
        } catch (MyException e) {
            e.getMessage();
            return null;
        }

    }


}
