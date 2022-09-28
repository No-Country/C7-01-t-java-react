package com.nocountry.findyourpet.repository;

import com.nocountry.findyourpet.models.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

    public UserEntity findByEmail(String email);
}
