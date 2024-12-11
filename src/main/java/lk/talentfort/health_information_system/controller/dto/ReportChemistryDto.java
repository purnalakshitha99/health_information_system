package lk.talentfort.health_information_system.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReportChemistryDto {

    private Long reportId;
    private List<Long> chemistryIds;
}
