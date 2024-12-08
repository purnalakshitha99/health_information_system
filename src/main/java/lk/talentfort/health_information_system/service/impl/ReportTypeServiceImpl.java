package lk.talentfort.health_information_system.service.impl;

import lk.talentfort.health_information_system.controller.dto.ReportTypeDto;
import lk.talentfort.health_information_system.controller.response.ReportTypeResponse;
import lk.talentfort.health_information_system.model.ReportType;
import lk.talentfort.health_information_system.repository.ReportTypeRepository;
import lk.talentfort.health_information_system.service.ReportTypeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReportTypeServiceImpl implements ReportTypeService {

    private ModelMapper modelMapper;
    private ReportTypeRepository reportTypeRepository;

    @Override
    public ReportTypeResponse createReport(ReportTypeDto reportTypeDto) {

        ReportType reportType = modelMapper.map(reportTypeDto,ReportType.class);

        reportTypeRepository.save(reportType);

        return modelMapper.map(reportType, ReportTypeResponse.class);
    }
}
