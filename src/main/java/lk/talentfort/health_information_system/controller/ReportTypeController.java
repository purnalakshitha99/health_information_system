package lk.talentfort.health_information_system.controller;


import jakarta.annotation.security.RolesAllowed;
import lk.talentfort.health_information_system.controller.dto.ReportTypeDto;
import lk.talentfort.health_information_system.controller.request.ReportTypeRq;
import lk.talentfort.health_information_system.controller.response.ReportTypeResponse;
import lk.talentfort.health_information_system.service.ReportTypeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ReportTypeController {

    private ReportTypeService reportTypeService;
    private ModelMapper modelMapper;

    @RolesAllowed("ADMIN")
    @PostMapping("/admin/reports")
    public ReportTypeResponse createReportType(@RequestBody ReportTypeRq reportTypeRq){

        ReportTypeDto reportTypeDto = modelMapper.map(reportTypeRq,ReportTypeDto.class);

        return reportTypeService.createReport(reportTypeDto);

    }
}
