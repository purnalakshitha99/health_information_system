package lk.talentfort.health_information_system.controller;

import jakarta.annotation.security.RolesAllowed;
import lk.talentfort.health_information_system.controller.dto.ValuesDto;
import lk.talentfort.health_information_system.controller.request.ValuesRq;
import lk.talentfort.health_information_system.controller.response.ValuesResponse;
import lk.talentfort.health_information_system.exception.ChemistryNotFoundException;
import lk.talentfort.health_information_system.exception.ReportTypeNotFoundException;
import lk.talentfort.health_information_system.exception.UserNotFoundException;
import lk.talentfort.health_information_system.service.ValueService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ValueController {

    private ModelMapper modelMapper;
    private ValueService valueService;

    @RolesAllowed("ADMIN")
    @PostMapping("/admin/values")
    public ValuesResponse createValue(@RequestBody ValuesRq valuesRq)throws UserNotFoundException, ReportTypeNotFoundException, ChemistryNotFoundException {


        System.out.println("value: "+valuesRq.getValue());
        System.out.println("reportId: "+valuesRq.getReportId());
        System.out.println("person id: "+valuesRq.getPersonId());
        System.out.println("chemistry id: "+valuesRq.getChemistryId());
        ValuesDto valuesDto = modelMapper.map(valuesRq, ValuesDto.class);
        return valueService.createValue(valuesDto);
    }
}
