package com.nocountry.findyourpet.service;

import com.nocountry.findyourpet.exceptions.MyException;
import com.nocountry.findyourpet.models.entity.UserEntity;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.nocountry.findyourpet.utilities.Role.*;

@Service
public class UserService implements UserDetailsService {


    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Transactional
    public void register(String email,
                         String pass,
                         String pass2,
                         String name ,
                         String lastName ,
                         String phone ,
                         String facebookAccount
                         ) throws MyException{

        validation(email, pass, pass2, name,lastName,phone);
        UserEntity us = new UserEntity();

        us.setPassword(new BCryptPasswordEncoder().encode(pass));
        us.setEmail(email);
        us.setName(name);
        us.setLastName(lastName);
        us.setPhone(phone);
        us.setFacebookAccount(facebookAccount);
        us.setRole(USER);

        userRepo.save(us);
    }

    public void validation(String email,
                           String pass,
                           String pass2,
                           String name,
                           String lastName,
                           String phone) throws MyException {

        if (name == null || name.isEmpty()) {
            throw new MyException("El nombre no puede estar vacio");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new MyException("El apellido no puede estar vacio");
        }
        if (phone == null || phone.isEmpty()) {
            throw new MyException("El numero de telefono no puede estar vacio");
        }
        if (pass == null || pass.isEmpty() || pass.length()<5) {
            throw new MyException("El password no puede estar vacio y contener minimo 5 digitos");
        }
        if (!pass2.equals(pass)) {
            throw new MyException("Las contraseñas deben ser iguales");
        }
        //validacion de mail
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
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
