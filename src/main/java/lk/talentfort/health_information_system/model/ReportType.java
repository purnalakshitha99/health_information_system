package lk.talentfort.health_information_system.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "report_types")
public class ReportType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reportName;



}
