package lk.talentfort.health_information_system.controller;

import jakarta.annotation.security.RolesAllowed;
import lk.talentfort.health_information_system.controller.dto.ReportColumnDto;
import lk.talentfort.health_information_system.controller.request.ReportColumnRq;
import lk.talentfort.health_information_system.controller.response.ReportColumnResponse;
import lk.talentfort.health_information_system.exception.ReportTypeNotFoundException;
import lk.talentfort.health_information_system.model.ReportColumn;
import lk.talentfort.health_information_system.repository.ReportColumnRepository;
import lk.talentfort.health_information_system.service.ReportColumnService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReportColumnController {

    private ModelMapper modelMapper;
    private ReportColumnService reportColumnService;


    @RolesAllowed("ADMIN")
    @PostMapping("admin/reports/{report_id}/report_columns")
    public List<ReportColumnResponse>  createReportColumn(@PathVariable("report_id")Long reportId, @RequestBody List <ReportColumnRq> reportColumnRqList)throws ReportTypeNotFoundException {

        List<ReportColumnDto> reportColumnDtoList = reportColumnRqList.stream().map(reportColumnRq -> modelMapper.map(reportColumnRq,ReportColumnDto.class)).toList();

       return reportColumnService.createReportColumn(reportId,reportColumnDtoList);
    }

}
