package lk.talentfort.health_information_system.service.impl;

import lk.talentfort.health_information_system.controller.dto.ValuesDto;
import lk.talentfort.health_information_system.controller.response.ValuesResponse;
import lk.talentfort.health_information_system.exception.ChemistryNotFoundException;
import lk.talentfort.health_information_system.exception.ReportTypeNotFoundException;
import lk.talentfort.health_information_system.exception.UserNotFoundException;
import lk.talentfort.health_information_system.model.Chemistry;
import lk.talentfort.health_information_system.model.ReportType;
import lk.talentfort.health_information_system.model.User;
import lk.talentfort.health_information_system.model.Value;
import lk.talentfort.health_information_system.repository.ChemistryRepository;
import lk.talentfort.health_information_system.repository.ReportTypeRepository;
import lk.talentfort.health_information_system.repository.UserRepository;
import lk.talentfort.health_information_system.repository.ValueRepository;
import lk.talentfort.health_information_system.service.ValueService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


@Service
@AllArgsConstructor
public class ValueServiceImpl implements ValueService {

    private ModelMapper modelMapper;
    private ValueRepository valueRepository;
    private ChemistryRepository chemistryRepository;
    private ReportTypeRepository reportTypeRepository;
    private UserRepository userRepository;

    @Override
    public ValuesResponse createValue(ValuesDto valuesDto) throws UserNotFoundException, ReportTypeNotFoundException, ChemistryNotFoundException {


        System.out.println("value: "+valuesDto.getValue());
        System.out.println("reportId: "+valuesDto.getReportId());
        System.out.println("person id: "+valuesDto.getPersonId());
        System.out.println("chemistry id: "+valuesDto.getChemistryId());

        User user = userRepository.findById(valuesDto.getPersonId()).orElseThrow(
                ()-> new UserNotFoundException("that user not in a db")
        );

        ReportType reportType = reportTypeRepository.findById(valuesDto.getReportId()).orElseThrow(
                ()-> new ReportTypeNotFoundException("that report not in a db")
        );

        Chemistry chemistry = chemistryRepository.findById(valuesDto.getChemistryId()).orElseThrow(
                ()-> new ReportTypeNotFoundException("that chemistry not in a db")
        );



//        Value value = modelMapper.map(valuesDto,Value.class);

        Value value = new Value();
        value.setValue(valuesDto.getValue());
        value.setPersonId(user.getId());
        value.setReportId(reportType.getId());
        value.setChemistryId(chemistry.getId());

        valueRepository.save(value);

        return modelMapper.map(value, ValuesResponse.class);


    }
}
