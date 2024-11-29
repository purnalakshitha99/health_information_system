package lk.talentfort.health_information_system.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private ROLES roles;
}
