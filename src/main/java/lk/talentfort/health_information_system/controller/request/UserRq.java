package lk.talentfort.health_information_system.controller.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lk.talentfort.health_information_system.model.ROLES;
import lombok.Data;

@Data
public class UserRq {

    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private ROLES roles;
}