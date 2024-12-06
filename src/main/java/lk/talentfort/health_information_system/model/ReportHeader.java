package lk.talentfort.health_information_system.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class ReportHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String headers; // JSON string of headers

    @Column(nullable = false)
    private String data; // JSON string of data
}
