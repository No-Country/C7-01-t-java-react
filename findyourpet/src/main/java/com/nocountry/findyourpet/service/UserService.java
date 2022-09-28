package com.nocountry.findyourpet.service;

import com.nocountry.findyourpet.exceptions.MyException;
import com.nocountry.findyourpet.models.entity.PetEntity;
import com.nocountry.findyourpet.models.entity.UserEntity;
import com.nocountry.findyourpet.models.request.UserRequest;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.nocountry.findyourpet.utilities.Role.*;

@Service
public class    UserService implements UserDetailsService {


    private final UserRepo userRepo;
    private final PetRepo petRepo;

    @Autowired
    public UserService(UserRepo userRepo, PetRepo petRepo) {
        this.userRepo = userRepo;
        this.petRepo = petRepo;
    }

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
    public void register(UserRequest userRequest) throws MyException{

        validation(userRequest);
        UserEntity user = new UserEntity();

        user.setPassword(new BCryptPasswordEncoder().encode(userRequest.getPass()));
        user.setEmail(userRequest.getEmail());
        user.setName(userRequest.getName());
        user.setLastName(userRequest.getLastName());
        user.setPhone(userRequest.getPhone());
        user.setFacebookAccount(userRequest.getFacebookAccount());
        user.setRole(USER);
        user.setSoftDelete(false);

        userRepo.save(user);
    }

    public void validation(UserRequest user) throws MyException {

        if (user.getName() == null || user.getName() .isEmpty()) {
            throw new MyException("El nombre no puede estar vacio");
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new MyException("El apellido no puede estar vacio");
        }
        if (user.getPhone() == null || user.getPhone().isEmpty()) {
            throw new MyException("El numero de telefono no puede estar vacio");
        }
        if (user.getPass() == null || user.getPass().isEmpty() || user.getPass().length()<5) {
            throw new MyException("El password no puede estar vacio y contener minimo 5 digitos");
        }
        if (!user.getPass2().equals(user.getPass())) {
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
