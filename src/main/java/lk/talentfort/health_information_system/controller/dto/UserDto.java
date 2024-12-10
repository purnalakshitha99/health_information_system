package lk.talentfort.health_information_system.controller.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lk.talentfort.health_information_system.model.ROLES;
import lombok.Data;

@Data
public class UserDto {

    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private ROLES roles;

    //patient information

    private String patientId;
    private String address;
    private String description;
    private Integer age;
    private String nic;
    private String phoneNumber;


}
