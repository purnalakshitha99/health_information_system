package lk.talentfort.health_information_system.service;

import lk.talentfort.health_information_system.controller.dto.ValuesDto;
import lk.talentfort.health_information_system.controller.response.ValuesResponse;
import lk.talentfort.health_information_system.exception.ChemistryNotFoundException;
import lk.talentfort.health_information_system.exception.ReportTypeNotFoundException;
import lk.talentfort.health_information_system.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface ValueService  {
    ValuesResponse createValue(ValuesDto valuesDto) throws UserNotFoundException, ReportTypeNotFoundException, ChemistryNotFoundException;
}
