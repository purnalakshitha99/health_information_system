package lk.talentfort.health_information_system.controller.request;

import lombok.Data;

@Data
public class UserAuthRequestDTO {

    private String username;
    private String password;
}