package lk.talentfort.health_information_system.service;

import lk.talentfort.health_information_system.controller.dto.ReportColumnDto;
import lk.talentfort.health_information_system.controller.response.ReportColumnResponse;
import lk.talentfort.health_information_system.exception.ReportTypeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReportColumnService {
    List<ReportColumnResponse> createReportColumn(Long reportId, List<ReportColumnDto> reportColumnDtoList)throws ReportTypeNotFoundException;
}
