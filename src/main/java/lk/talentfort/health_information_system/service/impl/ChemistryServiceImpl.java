package lk.talentfort.health_information_system.service.impl;

import lk.talentfort.health_information_system.controller.dto.ChemistryDto;
import lk.talentfort.health_information_system.controller.response.ChemistryResponse;
import lk.talentfort.health_information_system.model.Chemistry;
import lk.talentfort.health_information_system.model.ReportColumn;
import lk.talentfort.health_information_system.repository.ChemistryRepository;
import lk.talentfort.health_information_system.service.ChemistryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ChemistryServiceImpl implements ChemistryService {

    private ModelMapper modelMapper;
    private ChemistryRepository chemistryRepository;

    @Override
    public List<ChemistryResponse> createChemistry(List<ChemistryDto> chemistryDtoList) {

        List<Chemistry> chemistryList = chemistryDtoList.stream().map(chemistryDto -> {
            Chemistry chemistry = modelMapper.map(chemistryDto,Chemistry.class);

            return chemistry;
        }).toList();

        chemistryRepository.saveAll(chemistryList);

        return chemistryList.stream().map(chemistry -> modelMapper.map(chemistry, ChemistryResponse.class)).toList() ;
    }
}
