package com.nocountry.findyourpet.models.mapper;


import com.nocountry.findyourpet.models.entity.UserEntity;
import com.nocountry.findyourpet.models.request.UserRequest;
import com.nocountry.findyourpet.models.response.UserResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import static com.nocountry.findyourpet.utilities.Role.*;

@Component
public class UserMapper {

    public UserEntity entity(UserRequest request){
        UserEntity entity = new UserEntity();
        entity.setEmail(request.getEmail());
        entity.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        entity.setName(request.getName());
        entity.setLastName(request.getLastName());
        entity.setRole(USER);
        entity.setPhone(request.getPhone());
        entity.setFacebookAccount(request.getFacebookAccount());
        return entity;
    }

    public UserResponse responseEntity(UserEntity entity){
        UserResponse response = new UserResponse();
        response.setName(entity.getName());
        response.setLastName(entity.getLastName());
        response.setPhone(entity.getPhone());
        response.setFacebookAccount(entity.getFacebookAccount());
        return response;
    }


}
