package lk.talentfort.health_information_system.service;

import lk.talentfort.health_information_system.controller.dto.UserDto;
import lk.talentfort.health_information_system.controller.response.UserResponse;
import lk.talentfort.health_information_system.exception.UserNotFoundException;

import java.util.List;

public interface UserService {




    UserResponse createUser(UserDto userDto);

    List<UserResponse> getAllUsers() throws UserNotFoundException;

    UserResponse getSpecificUser(Long userId)throws UserNotFoundException;

    UserResponse updateUser (Long userId,UserDto userDto)throws UserNotFoundException;
}
