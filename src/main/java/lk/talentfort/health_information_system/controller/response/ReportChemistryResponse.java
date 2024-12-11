package lk.talentfort.health_information_system.controller.response;

import lombok.Data;

import java.util.List;

@Data
public class ReportChemistryResponse {

    private Long reportId;
    private List<Long> chemistryIds;
}
