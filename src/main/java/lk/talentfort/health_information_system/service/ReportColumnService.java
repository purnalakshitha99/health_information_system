package lk.talentfort.health_information_system.service;

import lk.talentfort.health_information_system.controller.dto.ReportColumnDto;
import lk.talentfort.health_information_system.controller.response.ReportColumnResponse;
import org.springframework.stereotype.Service;

@Service
public interface ReportColumnService {
    ReportColumnResponse createReportColumn(Long reportId,ReportColumnDto reportColumnDto);
}
