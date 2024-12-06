package lk.talentfort.health_information_system.controller;


import jakarta.annotation.security.RolesAllowed;
import lk.talentfort.health_information_system.controller.dto.ReportHeadersDto;
import lk.talentfort.health_information_system.controller.dto.UserDto;
import lk.talentfort.health_information_system.controller.request.ReportHeadersRq;
import lk.talentfort.health_information_system.model.ReportHeader;
import lk.talentfort.health_information_system.service.ReportHeaderService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ReportHeaderController {

    private ModelMapper modelMapper;
    private final ReportHeaderService reportHeaderService;

    @RolesAllowed("ADMIN")
    @PostMapping("/users/saveReport")
    public ResponseEntity<String > saveReport(@RequestBody ReportHeadersRq reportHeadersRq){
        try {
            ReportHeadersDto reportHeadersDto = new ReportHeadersDto();

            reportHeadersDto.setHeaders(reportHeadersRq.getHeaders());
            reportHeadersDto.setData(reportHeadersRq.getData());

            reportHeaderService.saveReport(reportHeadersDto);

            return ResponseEntity.ok("Report saved successfully!");
        }catch (Exception e){
            return ResponseEntity.status(500).body("Error saving report: "+e.getMessage());
        }





    }
}
