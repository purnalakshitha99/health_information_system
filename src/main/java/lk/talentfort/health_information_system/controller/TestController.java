package lk.talentfort.health_information_system.controller;

import lk.talentfort.health_information_system.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestController {

    private UserService userService;


    @PostMapping("/test")
    public void create(){
        System.out.println("test new");

        userService.create();
    }


}
