package lk.talentfort.health_information_system.controller.dto;

import lombok.Data;

@Data
public class ReportColumnDto {

    private Long reportId;
    private String columnName;
    private String dataType;
}
