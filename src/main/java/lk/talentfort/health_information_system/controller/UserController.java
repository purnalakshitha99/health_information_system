package lk.talentfort.health_information_system.controller;


import jakarta.annotation.security.RolesAllowed;
import lk.talentfort.health_information_system.controller.dto.UserDto;
import lk.talentfort.health_information_system.controller.request.UserRq;
import lk.talentfort.health_information_system.controller.response.UserResponse;

import lk.talentfort.health_information_system.exception.UserNotFoundException;
import lk.talentfort.health_information_system.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;


@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;


    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRq userRq){


        System.out.println("role :"+userRq.getRoles());
        UserDto userDto = modelMapper.map(userRq,UserDto.class);


        UserResponse userResponse = userService.createUser(userDto);


        return ResponseEntity.created(URI.create("users")).body(userResponse);
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/admin/users")
    public List<ResponseEntity<List<UserResponse>>> getAllUsers()throws UserNotFoundException {

        List<UserResponse> userResponseList = userService.getAllUsers();

        return Collections.singletonList(new ResponseEntity<>(userResponseList, HttpStatus.FOUND));
    }

    @GetMapping("users/{user_id}")
    public UserResponse getSpecificUser(@PathVariable("user_id")Long userId)throws UserNotFoundException{

        return userService.getSpecificUser(userId);

    }

    @PutMapping("/users/{user_id}")
    public UserResponse updateUser(@PathVariable("user_id")Long userId,@RequestBody UserRq userRq)throws UserNotFoundException{

        UserDto userDto = modelMapper.map(userRq,UserDto.class);

        return userService.updateUser(userId,userDto);
    }

}
