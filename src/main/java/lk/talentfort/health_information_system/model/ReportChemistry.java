package lk.talentfort.health_information_system.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "report_chemistry")
public class ReportChemistry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long reportId;
    private Long chemistryId;
}
