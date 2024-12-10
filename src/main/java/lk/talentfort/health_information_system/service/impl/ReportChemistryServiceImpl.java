package lk.talentfort.health_information_system.service.impl;

import lk.talentfort.health_information_system.controller.dto.ReportChemistryDto;
import lk.talentfort.health_information_system.controller.response.ReportChemistryResponse;
import lk.talentfort.health_information_system.exception.CanNotCreateChemistryException;
import lk.talentfort.health_information_system.exception.ChemistryNotFoundException;
import lk.talentfort.health_information_system.exception.ReportTypeNotFoundException;
import lk.talentfort.health_information_system.model.Chemistry;
import lk.talentfort.health_information_system.model.ReportChemistry;
import lk.talentfort.health_information_system.model.ReportType;
import lk.talentfort.health_information_system.repository.ChemistryRepository;
import lk.talentfort.health_information_system.repository.ReportChemistryRepository;
import lk.talentfort.health_information_system.repository.ReportTypeRepository;
import lk.talentfort.health_information_system.service.ChemistryService;
import lk.talentfort.health_information_system.service.ReportChemistryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReportChemistryServiceImpl implements ReportChemistryService {

    private final ReportChemistryRepository reportChemistryRepository;
    private ModelMapper modelMapper;
    private ReportChemistryRepository repository;
    private ReportTypeRepository reportTypeRepository;
    private ChemistryRepository chemistryRepository;
    @Override
    public ReportChemistryResponse createReportChemistry(ReportChemistryDto reportChemistryDto) throws ReportTypeNotFoundException, ChemistryNotFoundException , CanNotCreateChemistryException {

        ReportType reportType = reportTypeRepository.findById(reportChemistryDto.getReportId()).orElseThrow(
                ()-> new ReportTypeNotFoundException("that report type is not in a db")
        );

        Chemistry chemistry = chemistryRepository.findById(reportChemistryDto.getChemistryId()).orElseThrow(
                ()-> new ChemistryNotFoundException("that chemistry not in a db")
        );

        List<ReportChemistry> existingReportChemistryList = reportChemistryRepository.findReportChemistriesByChemistryIdAndReportId(reportChemistryDto.getChemistryId(), reportChemistryDto.getReportId());

        if (!existingReportChemistryList.isEmpty()){
            throw new CanNotCreateChemistryException("all ready in the that Chemistry");
        }

//        ReportChemistry reportChemistry = modelMapper.map(reportChemistryDto,ReportChemistry.class);

        ReportChemistry reportChemistry = new ReportChemistry();

        reportChemistry.setChemistryId(reportChemistryDto.getChemistryId());
        reportChemistry.setReportId(reportChemistryDto.getReportId());

        reportChemistryRepository.save(reportChemistry);

        return modelMapper.map(reportChemistry, ReportChemistryResponse.class);
    }
}
