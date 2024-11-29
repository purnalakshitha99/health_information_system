package lk.talentfort.health_information_system.service.impl;

import lk.talentfort.health_information_system.controller.dto.UserDto;
import lk.talentfort.health_information_system.controller.response.UserResponse;
import lk.talentfort.health_information_system.model.User;
import lk.talentfort.health_information_system.repository.UserRepository;
import lk.talentfort.health_information_system.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;



    @Override
    public UserResponse createUser(UserDto userDto) {

        User user = modelMapper.map(userDto,User.class);
        userRepository.save(user);

        return modelMapper.map(user,UserResponse.class);
    }
}
