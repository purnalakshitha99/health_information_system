package lk.talentfort.health_information_system.controller.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lk.talentfort.health_information_system.model.ROLES;
import lombok.Data;

@Data
public class UserResponse {

    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private ROLES roles;
}
