package lk.talentfort.health_information_system.service.impl;

import lk.talentfort.health_information_system.controller.dto.UserDto;
import lk.talentfort.health_information_system.controller.response.UserResponse;
import lk.talentfort.health_information_system.exception.UserNotFoundException;
import lk.talentfort.health_information_system.model.User;
import lk.talentfort.health_information_system.repository.UserRepository;
import lk.talentfort.health_information_system.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    public UserResponse createUser(UserDto userDto) {

        User user = modelMapper.map(userDto,User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);

        return modelMapper.map(user,UserResponse.class);
    }


    public List<UserResponse> getUsers()throws UserNotFoundException {

        List<User> userList = userRepository.findAll();

        if (userList.isEmpty()){
            throw new UserNotFoundException("Users not found user table are empty");
        }

        return userList.stream().map(user -> modelMapper.map(user,UserResponse.class)).toList();
    }
}
