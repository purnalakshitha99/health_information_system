package lk.talentfort.health_information_system.controller;



import jakarta.annotation.security.RolesAllowed;

import lk.talentfort.health_information_system.controller.request.UserAuthRequestDTO;
import lk.talentfort.health_information_system.controller.response.UserLoginResponseDTO;
import lk.talentfort.health_information_system.exception.InvalidCredentialsException;
import lk.talentfort.health_information_system.exception.UserNotFoundException;
import lk.talentfort.health_information_system.model.User;
import lk.talentfort.health_information_system.repository.UserRepository;
import lk.talentfort.health_information_system.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@AllArgsConstructor
public class AuthController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    @PostMapping("/authenticate")
    public UserLoginResponseDTO authenticate(@RequestBody UserAuthRequestDTO requestDTO) throws UserNotFoundException, InvalidCredentialsException {
        System.out.println(" ====authenticate user " + requestDTO.getUsername());

        String email = requestDTO.getUsername();

       Optional<User> userOptional = Optional.ofNullable(userRepository.findUserByEmail(email));

        List<String> roles = new ArrayList<>();
       if (userOptional.isEmpty()){
           throw new UserNotFoundException("that user not in a db");
       }else{
           User user = userOptional.get();

           roles.add("ROLE_"+user.getRoles());

           //authenticated user

           user.setUsername(requestDTO.getUsername());
           System.out.println("password : "+user.getPassword());
           System.out.println("password req : "+requestDTO.getPassword());
           System.out.println("role"+user.getRoles());
           System.out.println("username"+user.getUsername());

           // Check if the provided password matches the stored hashed password
           if (!passwordEncoder.matches(requestDTO.getPassword(), user.getPassword())) {
               throw new InvalidCredentialsException("Invalid credentials");
           }

           Map<String, Object> extraClaims = new HashMap<>();
           extraClaims.put("username", user.getEmail());
           extraClaims.put("roles", user.getRoles());
           extraClaims.put("name", user.getName());

           String token = jwtService.generateToken(user, extraClaims);
           return UserLoginResponseDTO.builder()
                   .token(token)
                   .build();
       }




//        List<String> roles = new ArrayList<>();
//        if (requestDTO.getUsername().equals("user1")) {
//            roles.add("ROLE_USER");
//
//            System.out.println("user1 awa");
//        }
//        if (requestDTO.getUsername().equals("user2")) {
////            roles.addAll(List.of("ROLE_USER", "ROLE_ADMIN"));
//            roles.add("ROLE_ADMIN");
//        }


    }

//    @RolesAllowed("ROLE_ADMIN")
    @RolesAllowed("ADMIN")
    @PostMapping("/admin")
    public String sayHiAdmin(){
        return "Hello Admin";
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/admin")
    public String sayHiAdminGet(){
        return "Hello get Admin";
    }


    @RolesAllowed({"USER_LEVEL1","ADMIN"})
    @PostMapping("/user")
    public String sayHiUser(){
        return "Hello user level1";
    }

    @RolesAllowed({"USER_LEVEL1","ADMIN"})
    @GetMapping("/user/get_users")
    public String sayHiUserGet(){
        return "Hello user  get level1";
    }

    @RolesAllowed({"USER_LEVEL2","ADMIN"})
    @PostMapping("/special_users")
    public String sayHiSpecialUser(){
        return "Hello  special user";
    }

    @RolesAllowed({"NURSE_LEVEL1","ADMIN"})
    @PostMapping("/nurses")
    public String sayHiNurses(){
        return "Hello nurses";
    }

    @RolesAllowed({"NURSE_LEVEL2","ADMIN"})
    @PostMapping("/special_nurses")
    public String sayHiSpecialNurses(){
        return " Hello special nurses ";
    }

    @RolesAllowed({"DOCTOR_LEVEL1","ADMIN"})
    @PostMapping("/doctors")
    public String sayHiDoctors(){
        return " Hello  Doctor ";
    }

    @RolesAllowed({"DOCTOR_LEVEL2","ADMIN"})
    @PostMapping("/special_doctors")
    public String sayHiSpecialDoctors(){
        return " Hello Special Doctor ";
    }
}

