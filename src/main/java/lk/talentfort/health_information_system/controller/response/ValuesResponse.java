package lk.talentfort.health_information_system.controller.response;

import lombok.Data;

@Data
public class ValuesResponse {

    private Long id;
    private Long personId;
    private Long reportId;
    private Long chemistryId;
    private Float value;
}
