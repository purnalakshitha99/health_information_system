package lk.talentfort.health_information_system.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/test")
    public void test(){
        System.out.println("test new");
    }


}
