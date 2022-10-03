package com.nocountry.findyourpet.models.mapper;

import com.nocountry.findyourpet.models.entity.PetEntity;
import com.nocountry.findyourpet.models.request.PetRequest;
import com.nocountry.findyourpet.models.response.PetResponse;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {

   public PetEntity entity(PetRequest petRequest){
       PetEntity pe = new PetEntity();
       pe.setName(petRequest.getName());
       pe.setPhoto(petRequest.getPhoto());
       pe.setAge(petRequest.getAge());
       pe.setDescription(petRequest.getDescription());
       pe.setColor(petRequest.getColor());
       pe.setLocation(petRequest.getLocation());
       pe.setSpecies(petRequest.getSpecies());
       pe.setSex(petRequest.getSex());
       pe.setSize(petRequest.getSize());
       pe.setDate(petRequest.getDate());
       pe.setTail(petRequest.getTail());
       pe.setEars(petRequest.getEars());
       return pe;
   }

   public PetResponse response(PetEntity pe){
       PetResponse response = new PetResponse();
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
       return response;
   }




}
