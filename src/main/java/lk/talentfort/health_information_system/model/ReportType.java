package lk.talentfort.health_information_system.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "report_types")
public class ReportType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reportName;
    private Float price;

    @OneToMany(mappedBy = "reportType")
    private List<ReportColumn> reportColumnList;


}
