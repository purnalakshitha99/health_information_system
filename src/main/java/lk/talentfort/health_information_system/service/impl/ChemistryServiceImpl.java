package lk.talentfort.health_information_system.service.impl;

import lk.talentfort.health_information_system.controller.dto.ChemistryDto;
import lk.talentfort.health_information_system.controller.response.ChemistryResponse;
import lk.talentfort.health_information_system.model.Chemistry;
import lk.talentfort.health_information_system.repository.ChemistryRepository;
import lk.talentfort.health_information_system.service.ChemistryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ChemistryServiceImpl implements ChemistryService {

    private ModelMapper modelMapper;
    private ChemistryRepository chemistryRepository;

    @Override
    public ChemistryResponse createChemistry(ChemistryDto chemistryDto) {

        Chemistry chemistry = modelMapper.map(chemistryDto, Chemistry.class);
        chemistryRepository.save(chemistry);

        return modelMapper.map(chemistry, ChemistryResponse.class);
    }
}
