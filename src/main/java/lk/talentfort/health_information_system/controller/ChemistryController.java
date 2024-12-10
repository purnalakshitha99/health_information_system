package lk.talentfort.health_information_system.controller;


import lk.talentfort.health_information_system.controller.dto.ChemistryDto;
import lk.talentfort.health_information_system.controller.request.ChemistryRq;
import lk.talentfort.health_information_system.controller.response.ChemistryResponse;
import lk.talentfort.health_information_system.service.ChemistryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ChemistryController {

    private ModelMapper modelMapper;
    private ChemistryService chemistryService;

    @PostMapping("/admin/chemistry")
    public ChemistryResponse createChemistry(@RequestBody ChemistryRq chemistryRq){

        ChemistryDto chemistryDto = modelMapper.map(chemistryRq,ChemistryDto.class);
        return chemistryService.createChemistry(chemistryDto);
    }
}
