package com.nocountry.findyourpet.service;


import com.nocountry.findyourpet.exceptions.MyException;
import com.nocountry.findyourpet.models.entity.PetEntity;
import com.nocountry.findyourpet.models.entity.UserEntity;
import com.nocountry.findyourpet.models.request.PetRequest;
import com.nocountry.findyourpet.models.response.PetResponse;
import com.nocountry.findyourpet.repository.PetRepo;
import com.nocountry.findyourpet.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepo petRepo;
    @Autowired
    private UserRepo userRepo;
    @Transactional
    public PetEntity register(PetRequest petRequest,Long idUser) throws MyException {

        validation(petRequest);
        PetEntity pe = new PetEntity();
        pe.setOwner(findOwner(idUser));
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
        pe.setTail(petRequest.getTail());
        pe.setEars(petRequest.getEars());
        petRepo.save(pe);
        return pe;
    }
    @Transactional
    public PetEntity modify(PetRequest petRequest,Long idPet) throws MyException {
        validation(petRequest);
        //se busca si la mascota esta en la base de datos
        Optional<PetEntity> response = petRepo.findById(idPet);
        if (response.isPresent()){
            PetEntity pe = response.get();
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
            pe.setTail(petRequest.getTail());
            pe.setEars(petRequest.getEars());

            petRepo.save(pe);
            return pe;
        } else {
            throw new MyException("No se encuentra la mascota en la base");

        }

    }

    //metodo para listar mascotas
    @Transactional(readOnly = true)
    public List<PetResponse> getAll(){
        List<PetEntity> listPets = petRepo.findAll();
        List<PetResponse> listPetsResponse = new ArrayList<>();

        for (PetEntity aux: listPets) {
            PetResponse response = new PetResponse();
            response.setName(aux.getName());
            response.setPhoto(aux.getPhoto());
            response.setAge(aux.getAge());
            response.setDescription(aux.getDescription());
            response.setColor(aux.getColor());
            response.setLocation(aux.getLocation());
            response.setSpecies(aux.getSpecies());
            response.setSex(aux.getSex());
            response.setSize(aux.getSize());
            response.setDate(aux.getDate());
            response.setTail(aux.getTail());
            response.setEars(aux.getEars());
            listPetsResponse.add(response);
        }
        return listPetsResponse;
    }


    @Transactional(readOnly = true)
    public UserEntity findOwner(Long idUser) throws MyException {

        Optional<UserEntity> response = userRepo.findById(idUser);

        if (response.isPresent()){
            UserEntity owner = response.get();
            return owner;
        } else {
            throw new MyException("El id no corresponde a un usuario");
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
        if (petRequest.getTail().isEmpty() || petRequest.getTail() == null){
            throw new MyException("Debe colocar tipo de cola");
        }
        if (petRequest.getEars().isEmpty() || petRequest.getEars() == null){
            throw new MyException("Debe colocar tipo de oreja");
        }


    }




}
