package lk.talentfort.health_information_system.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "report_column")
public class ReportColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private Long reportId;
    private String columnName;
    private String dataType;

    @ManyToOne
    private ReportType reportType;
}
