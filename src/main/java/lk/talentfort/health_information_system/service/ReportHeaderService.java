package lk.talentfort.health_information_system.service;

import lk.talentfort.health_information_system.controller.dto.ReportHeadersDto;
import org.springframework.stereotype.Service;

@Service
public interface ReportHeaderService {
    void saveReport(ReportHeadersDto reportHeadersDto);
}
