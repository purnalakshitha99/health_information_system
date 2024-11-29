package lk.talentfort.health_information_system.service;

import lk.talentfort.health_information_system.controller.dto.UserDto;
import lk.talentfort.health_information_system.controller.response.UserResponse;

public interface UserService {




    UserResponse createUser(UserDto userDto);
}
