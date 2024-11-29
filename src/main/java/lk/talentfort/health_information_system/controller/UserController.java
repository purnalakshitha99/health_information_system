package lk.talentfort.health_information_system.controller;


import lk.talentfort.health_information_system.controller.dto.UserDto;
import lk.talentfort.health_information_system.controller.request.UserRq;
import lk.talentfort.health_information_system.controller.response.UserResponse;

import lk.talentfort.health_information_system.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


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

//    @RolesAllowed("ADMIN")
//    @GetMapping("/users/{user_id}")
//    public ResponseEntity<UserResponse> getSpecificUser(@PathVariable("user_id")Long userId)throws UserNotFoundException {
//
//        UserResponse userResponse  = userService.getSpecificUser(userId);
//
//        return new ResponseEntity<>(userResponse, HttpStatus.FOUND);
//    }
//
//    @RolesAllowed({"USER","ADMIN"})
//    @PutMapping("/users/{user_id}")
//    public ResponseEntity<UserResponse> updateUser(@PathVariable("user_id")Long userID, @RequestBody UserRq userRq)throws UserNotFoundException {
//
//        UserDto userDto = modelMapper.map(userRq,UserDto.class);
//        UserResponse userResponse = userService.updateUser(userID,userDto);
//
//        return new ResponseEntity<>(userResponse,HttpStatus.CREATED);
//    }
//
//    @RolesAllowed("ADMIN")
//    @DeleteMapping("/users/{user_id}")
//    public ResponseEntity<UserResponse> deleteSpecificUser(@PathVariable("user_id")Long userId)throws UserNotFoundException{
//
//        UserResponse userResponse = userService.deleteSpecificUser(userId);
//
//        return new ResponseEntity<>(userResponse,HttpStatus.FOUND);
//    }
//
//    @RolesAllowed("ADMIN")
//    @GetMapping("/users")
//    public List<ResponseEntity<List<UserResponse>>> getAllUsers()throws UserNotFoundException {
//
//        List<UserResponse> userResponseList = userService.getAllUsers();
//
//        return Collections.singletonList(new ResponseEntity<>(userResponseList, HttpStatus.FOUND));
//    }

}
