package lk.talentfort.health_information_system.controller.request;

import lombok.Data;

@Data
public class ReportColumnRq {

    private Long reportId;
    private String columnName;
    private String dataType;
}
