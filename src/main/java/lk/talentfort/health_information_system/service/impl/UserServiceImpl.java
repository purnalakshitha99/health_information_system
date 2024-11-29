package lk.talentfort.health_information_system.service.impl;

import lk.talentfort.health_information_system.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    public void create(){
        System.out.println("impl");
    }


}
