package lk.talentfort.health_information_system.controller;

import jakarta.annotation.security.RolesAllowed;
import lk.talentfort.health_information_system.controller.dto.ReportChemistryDto;
import lk.talentfort.health_information_system.controller.request.ReportChemistryRq;
import lk.talentfort.health_information_system.controller.response.ReportChemistryResponse;
import lk.talentfort.health_information_system.exception.CanNotCreateChemistryException;
import lk.talentfort.health_information_system.exception.ChemistryNotFoundException;
import lk.talentfort.health_information_system.exception.ReportTypeNotFoundException;
import lk.talentfort.health_information_system.service.ReportChemistryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@AllArgsConstructor
public class ReportChemistryController {

    private final ReportChemistryService reportChemistryService;
    private final ModelMapper modelMapper;

    @RolesAllowed("ADMIN")
    @PostMapping("/admin/report_chemistry")
    public ResponseEntity<ReportChemistryResponse> createReportChemistry(@RequestBody ReportChemistryRq reportChemistryRq)throws ReportTypeNotFoundException, ChemistryNotFoundException, CanNotCreateChemistryException {

        ReportChemistryDto reportChemistryDto = modelMapper.map(reportChemistryRq, ReportChemistryDto.class);
        ReportChemistryResponse reportChemistryResponse = reportChemistryService.createReportChemistry(reportChemistryDto);

        return ResponseEntity.created(URI.create("admin/report_chemistry")).body(reportChemistryResponse);
    }
}
