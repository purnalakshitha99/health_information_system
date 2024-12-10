package lk.talentfort.health_information_system.service.impl;


import lk.talentfort.health_information_system.controller.dto.ReportColumnDto;
import lk.talentfort.health_information_system.controller.response.ReportColumnResponse;
import lk.talentfort.health_information_system.exception.ReportTypeNotFoundException;
import lk.talentfort.health_information_system.model.ReportColumn;
import lk.talentfort.health_information_system.model.ReportType;
import lk.talentfort.health_information_system.repository.ReportColumnRepository;
import lk.talentfort.health_information_system.repository.ReportTypeRepository;
import lk.talentfort.health_information_system.service.ReportColumnService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor



public class ReportColumnServiceImpl implements ReportColumnService {

    private ModelMapper modelMapper;
    private ReportColumnRepository reportColumnRepository;
    private ReportTypeRepository reportTypeRepository;

    public List<ReportColumnResponse> createReportColumn(Long reportId, List<ReportColumnDto> reportColumnDtoList)throws ReportTypeNotFoundException {

        ReportType reportType = reportTypeRepository.findById(reportId).orElseThrow(
                ()-> new ReportTypeNotFoundException("that Report type is not in a db")
        );

        List<ReportColumn> reportColumnList = reportColumnDtoList.stream().map(reportColumnDto -> {
            ReportColumn reportColumn = modelMapper.map(reportColumnDto,ReportColumn.class);
            reportColumn.setReportType(reportType);
            return reportColumn;
        }).toList();


        reportColumnRepository.saveAll(reportColumnList);



        return reportColumnList.stream().map(reportColumn -> modelMapper.map(reportColumn,ReportColumnResponse.class)).toList();


    }

}







