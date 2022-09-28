package com.nocountry.findyourpet.repository;

import com.nocountry.findyourpet.models.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PetRepo extends JpaRepository<Pet, Long> {

}
