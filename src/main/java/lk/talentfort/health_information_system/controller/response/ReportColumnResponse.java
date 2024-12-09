package lk.talentfort.health_information_system.controller.response;

import lombok.Data;

@Data
public class ReportColumnResponse {

    private Long reportId;
    private String columnName;
    private String dataType;
}
