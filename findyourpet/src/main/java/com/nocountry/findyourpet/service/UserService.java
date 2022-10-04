package com.nocountry.findyourpet.service;

import com.nocountry.findyourpet.exceptions.MyException;
import com.nocountry.findyourpet.models.entity.PetEntity;
import com.nocountry.findyourpet.models.entity.UserEntity;
import com.nocountry.findyourpet.models.mapper.UserMapper;
import com.nocountry.findyourpet.models.request.UserRequest;
import com.nocountry.findyourpet.models.response.UserResponse;
import com.nocountry.findyourpet.repository.PetRepo;
import com.nocountry.findyourpet.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
        UserEntity user = userRepo.findByEmail(email);
        List<PetEntity> pets = user.getPets();

        Optional<PetEntity> response = petRepo.findById(idPet);
        if(response.isPresent()){
            pets.add(response.get());
            user.setPets(pets);
            userRepo.save(user);
        }
    }

    @Transactional
    public UserResponse register(UserRequest userRequest) throws MyException{
        validation(userRequest);
        UserEntity user = userMapper.entity(userRequest);
        userRepo.save(user);
        UserResponse response= userMapper.responseEntity(user);
        return response;
    }

    @Transactional
    public UserResponse modify(UserRequest request, Long userId) throws MyException {
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
            UserResponse response = userMapper.responseEntity(entity);
            return response;
        } else {
            throw new MyException("No se encuentra el id en la base de datos");
        }

    }
    private void validation(UserRequest user) throws MyException {

        if (user.getName() == null || user.getName() .isEmpty()) {
            throw new MyException("El nombre no puede estar vacio");
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new MyException("El apellido no puede estar vacio");
        }
        if (user.getPhone() == null || user.getPhone().isEmpty()) {
            throw new MyException("El numero de telefono no puede estar vacio");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty() || user.getPassword().length()<5) {
            throw new MyException("El password no puede estar vacio y contener minimo 5 digitos");
        }
        if (!user.getPassword2().equals(user.getPassword())) {
            throw new MyException("Las contraseñas deben ser iguales");
        }
        //validacion de mail
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user.getEmail());
        if (!matcher.matches()) {
            throw new MyException("Ingrese un mail valido");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByEmail(email);

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
