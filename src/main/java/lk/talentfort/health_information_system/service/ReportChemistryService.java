package lk.talentfort.health_information_system.service;

import lk.talentfort.health_information_system.controller.dto.ReportChemistryDto;
import lk.talentfort.health_information_system.controller.response.ReportChemistryResponse;
import lk.talentfort.health_information_system.exception.CanNotCreateChemistryException;
import lk.talentfort.health_information_system.exception.ChemistryNotFoundException;
import lk.talentfort.health_information_system.exception.ReportTypeNotFoundException;

public interface ReportChemistryService {
    ReportChemistryResponse createReportChemistry(ReportChemistryDto reportChemistryDto)throws ReportTypeNotFoundException, ChemistryNotFoundException, CanNotCreateChemistryException;
}
