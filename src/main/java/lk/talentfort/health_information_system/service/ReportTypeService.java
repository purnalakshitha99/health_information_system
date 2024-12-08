package lk.talentfort.health_information_system.service;

import lk.talentfort.health_information_system.controller.dto.ReportTypeDto;
import lk.talentfort.health_information_system.controller.response.ReportTypeResponse;
import org.springframework.stereotype.Service;

@Service
public interface ReportTypeService {
    ReportTypeResponse createReport(ReportTypeDto reportTypeDto);
}
