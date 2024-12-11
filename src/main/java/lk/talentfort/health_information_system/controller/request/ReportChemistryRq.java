package lk.talentfort.health_information_system.controller.request;

import lombok.Data;

import java.util.List;

@Data
public class ReportChemistryRq {

    private Long reportId;
    private List<Long> chemistryIds;
}
