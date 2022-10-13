package com.nocountry.findyourpet.service;

import com.nocountry.findyourpet.exceptions.MyException;
import com.nocountry.findyourpet.models.entity.PetEntity;
import com.nocountry.findyourpet.models.entity.UserEntity;
import com.nocountry.findyourpet.models.mapper.UserMapper;
import com.nocountry.findyourpet.models.request.LoginRequest;
import com.nocountry.findyourpet.models.request.UserRequest;
import com.nocountry.findyourpet.models.response.LoginResponse;
import com.nocountry.findyourpet.models.response.UserResponse;
import com.nocountry.findyourpet.repository.PetRepo;
import com.nocountry.findyourpet.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.nocountry.findyourpet.utilities.Role.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PetRepo petRepo;
    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void addPetToList(Long idPet, String email){
        Optional<UserEntity> response = userRepo.findByEmail(email);
        if (response.isPresent()){
          UserEntity user = response.get();
          List<PetEntity> pets = user.getPets();

          Optional<PetEntity> response2 = petRepo.findById(idPet);
            if(response2.isPresent()){
                pets.add(response2.get());
                user.setPets(pets);
                userRepo.save(user);
            }
        }

    }

    @Transactional
    public ResponseEntity<?> register(UserRequest userRequest) throws MyException{
        validation(userRequest);
        UserEntity user = userMapper.entity(userRequest);
        userRepo.save(user);
        return new ResponseEntity<>("user registred succesfully" , HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> login(LoginRequest request){
        Optional<UserEntity> entity = userRepo.findByEmail(request.getEmail());

        if (entity.isPresent()){
            LoginResponse response = new LoginResponse();
            response.setEmail(entity.get().getEmail());
            response.setName(entity.get().getName());
            response.setOk(true);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("incorrect email or password" , HttpStatus.NOT_FOUND);
        }

    }


    @Transactional
    public ResponseEntity<?> modify(UserRequest request, Long userId) throws MyException {
        Optional<UserEntity> findEntity = userRepo.findById(userId);
        if (findEntity.isPresent()){
            validation(request);
            UserEntity entity = findEntity.get();
            entity.setEmail(request.getEmail());
            entity.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
            entity.setName(request.getName());
            entity.setLastName(request.getLastName());
            entity.setRole(USER);
            entity.setPhone(request.getPhone());
            entity.setFacebookAccount(request.getFacebookAccount());

            userRepo.save(entity);
            return new ResponseEntity<>("successfully modified" , HttpStatus.OK);
        } else {
            return new ResponseEntity<>("id not found in database" , HttpStatus.NOT_FOUND);
        }
    }
    private void validation(UserRequest user) throws MyException {

        if (user.getName() == null || user.getName() .isEmpty()) {
            throw new MyException("name is not empty");
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new MyException("lastname is not empty");
        }
        if (user.getPhone() == null || user.getPhone().isEmpty()) {
            throw new MyException("phone is not empty");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty() || user.getPassword().length()<5) {
            throw new MyException("password is not empty and must contain 5 digits");
        }
        if (!user.getPassword2().equals(user.getPassword())) {
            throw new MyException("password are equals");
        }
        //validacion de mail
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user.getEmail());
        if (!matcher.matches()) {
            throw new MyException("please insert email valid");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> response = userRepo.findByEmail(email);
        UserEntity user = response.get();

        if(user != null){

            List<GrantedAuthority> permission = new ArrayList<>();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + user.getRole().toString());
            permission.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession sesion = attr.getRequest().getSession(true);

            sesion.setAttribute("usuarioSesion",user);

            return new User(user.getEmail(),user.getPassword(),permission);
        }
        return null;
    }
}
