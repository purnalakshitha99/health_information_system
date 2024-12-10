package lk.talentfort.health_information_system.controller.request;

import lombok.Data;

@Data
public class ValuesRq {

    private Long personId;
    private Long reportId;
    private Long chemistryId;
    private Float value;
}
