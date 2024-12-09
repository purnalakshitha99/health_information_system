package lk.talentfort.health_information_system.service.impl;

import lk.talentfort.health_information_system.controller.dto.ReportColumnDto;
import lk.talentfort.health_information_system.controller.request.ReportColumnRq;
import lk.talentfort.health_information_system.controller.response.ReportColumnResponse;
import lk.talentfort.health_information_system.model.ReportColumn;
import lk.talentfort.health_information_system.repository.ReportColumnRepository;
import lk.talentfort.health_information_system.service.ReportColumnService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class ReportColumnServiceImpl implements ReportColumnService {

    private ModelMapper modelMapper;
    private ReportColumnRepository reportColumnRepository;

    public ReportColumnResponse createReportColumn(Long reportId, ReportColumnDto reportColumnDto){

        ReportColumn reportColumn = modelMapper.map(reportColumnDto,ReportColumn.class);
        reportColumn.setReportId(reportId);

        reportColumnRepository.save(reportColumn);

        return modelMapper.map(reportColumn,ReportColumnResponse.class);


    }
}
