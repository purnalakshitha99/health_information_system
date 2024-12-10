package lk.talentfort.health_information_system.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "test_master")
public class Value {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long personId;
    private Long reportId;
    private Long chemistryId;
    private Float value;
}
