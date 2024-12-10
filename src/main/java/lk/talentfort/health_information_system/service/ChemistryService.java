package lk.talentfort.health_information_system.service;

import lk.talentfort.health_information_system.controller.dto.ChemistryDto;
import lk.talentfort.health_information_system.controller.response.ChemistryResponse;

import java.util.List;

public interface ChemistryService {
    List<ChemistryResponse> createChemistry(List<ChemistryDto> chemistryDto);
}
