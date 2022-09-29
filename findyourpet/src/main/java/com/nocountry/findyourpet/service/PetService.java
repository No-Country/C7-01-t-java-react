package com.nocountry.findyourpet.service;


import com.nocountry.findyourpet.exceptions.MyException;
import com.nocountry.findyourpet.models.entity.PetEntity;
import com.nocountry.findyourpet.models.entity.UserEntity;
import com.nocountry.findyourpet.models.request.PetRequest;
import com.nocountry.findyourpet.repository.PetRepo;
import com.nocountry.findyourpet.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepo petRepo;
    @Autowired
    private UserRepo userRepo;
    @Transactional
    public void register(PetRequest petRequest,Long idUser) throws MyException {

        validation(petRequest);
        PetEntity pe = new PetEntity();

        pe.setName(petRequest.getName());
        pe.setPhoto(petRequest.getPhoto());
        pe.setAge(petRequest.getAge());
        pe.setDescription(petRequest.getDescription());
        pe.setColor(petRequest.getColor());
        pe.setLocation(petRequest.getLocation());
        pe.setName(petRequest.getName());
        pe.setSpecies(petRequest.getSpecies());
        pe.setSex(petRequest.getSex());
        pe.setSize(petRequest.getSize());
        pe.setDate(petRequest.getDate());
        pe.setTail(petRequest.getTails());
        pe.setEars(petRequest.getEars());

       //validacion de si pertenece a un dueño
        pe.setOwner(findOwner(idUser));

        petRepo.save(pe);




    }

    //debo tener long de pet id para verificar a su dueño
    public UserEntity findOwner(Long idUser) throws MyException {

        Optional<UserEntity> response = userRepo.findById(idUser);

        if (response.isPresent()){
            UserEntity owner = response.get();
            return owner;
        } else {
            throw new MyException("El id no corresponde al del dueño");
        }


    }


    private void validation(PetRequest petRequest) throws MyException {
          if (petRequest.getName().isEmpty() || petRequest.getName() == null){
              throw new MyException("El nombre no puede estar vacio ni ser nulo");
          }
        if (petRequest.getPhoto().isEmpty() || petRequest.getPhoto() == null){
            throw new MyException("Debe poseer una imagen");
        }
        if (petRequest.getAge() == null){
            throw new MyException("La edad no puede ser nula");
        }
        if (petRequest.getDescription().isEmpty() || petRequest.getDescription() == null){
            throw new MyException("Debe poseer descripcion");
        }
        if (petRequest.getColor().isEmpty() || petRequest.getColor() == null){
            throw new MyException("Debe poseer color");
        }
        if (petRequest.getLocation().isEmpty() || petRequest.getLocation() == null){
            throw new MyException("Debe contener en que zona se perdió");
        }
        if (petRequest.getSpecies().isEmpty() || petRequest.getSpecies() == null){
            throw new MyException("Debe especificarse la especie");
        }
        if (petRequest.getSex().isEmpty() || petRequest.getSex() == null){
            throw new MyException("Debe poseer sexo, ya sea otro");
        }
        if (petRequest.getSize().isEmpty() || petRequest.getSize() == null){
            throw new MyException("Debe especificarse el tamaño");
        }
        if (petRequest.getDate() == null){
            throw new MyException("Debe colocar una fecha");
        }
        if (petRequest.getTails().isEmpty() || petRequest.getTails() == null){
            throw new MyException("Debe colocar tipo de cola");
        }
        if (petRequest.getEars().isEmpty() || petRequest.getEars() == null){
            throw new MyException("Debe colocar tipo de oreja");
        }


    }




}
