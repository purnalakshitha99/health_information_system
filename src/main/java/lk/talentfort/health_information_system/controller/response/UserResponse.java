package lk.talentfort.health_information_system.controller.response;

import lombok.Data;

@Data
public class UserResponse {

    private String name;
    private String email;
    private String password;
    private String role;
}
