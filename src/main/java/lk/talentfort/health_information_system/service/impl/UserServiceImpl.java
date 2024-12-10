package lk.talentfort.health_information_system.service.impl;

import lk.talentfort.health_information_system.controller.dto.UserDto;
import lk.talentfort.health_information_system.controller.response.MessageResponse;
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

        if (userDto.getRoles().equals("ROLE_PATIENT")){

            String latestPatientId = userRepository.findLatestPatientId(); // Custom query to get the latest patient ID
            String newPatientId = generateNewPatientId(latestPatientId);
            user.setPatientId(newPatientId); // Assuming User entity has a field 'patientId'
        }
        userRepository.save(user);

        return modelMapper.map(user,UserResponse.class);
    }

    private String generateNewPatientId(String latestPatientId) {

        if (latestPatientId == null) {
            return "P001"; // Start from P01 if no patient IDs exist
        }
        int numberPart = Integer.parseInt(latestPatientId.substring(1)); // Extract the numeric part
        String newId = String.format("P%02d", numberPart + 1); // Increment and format with leading zero
        return newId;
    }

    @Override
    public List<UserResponse> getAllUsers() throws UserNotFoundException {

        List<User> userList = userRepository.findAll();

        if (userList.isEmpty()){

            throw new UserNotFoundException("user table is empty");
        }

       return userList.stream().map(user -> modelMapper.map(user,UserResponse.class)).toList();

    }


    public UserResponse getSpecificUser(Long userId)throws UserNotFoundException{

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("that user not in a db")
        );

       return modelMapper.map(user,UserResponse.class);
    }

    public UserResponse updateUser(Long userId,UserDto userDto) throws UserNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("that user not in a db")
        );

        modelMapper.map(userDto,user);

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));


        userRepository.save(user);

        return modelMapper.map(user,UserResponse.class);
    }

    @Override
    public MessageResponse deleteUser(Long userId) throws UserNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("that user not in a db")
        );

        userRepository.deleteById(userId);

        MessageResponse messageResponse = new MessageResponse();

        messageResponse.setMessage("user delete successfully");
        messageResponse.setUserResponse(modelMapper.map(user, UserResponse.class));

        return messageResponse;
    }

}
