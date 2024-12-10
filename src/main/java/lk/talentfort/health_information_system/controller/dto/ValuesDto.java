package lk.talentfort.health_information_system.controller.dto;

import lombok.Data;

@Data
public class ValuesDto {

    private Long personId;
    private Long reportId;
    private Long chemistryId;
    private Float value;
}
