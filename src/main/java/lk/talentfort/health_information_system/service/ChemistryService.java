package lk.talentfort.health_information_system.service;

import lk.talentfort.health_information_system.controller.dto.ChemistryDto;
import lk.talentfort.health_information_system.controller.response.ChemistryResponse;

public interface ChemistryService {
    ChemistryResponse createChemistry(ChemistryDto chemistryDto);
}
