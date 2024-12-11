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

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportChemistryServiceImpl implements ReportChemistryService {

    private final ReportChemistryRepository reportChemistryRepository;
    private ModelMapper modelMapper;
    private ReportTypeRepository reportTypeRepository;
    private ChemistryRepository chemistryRepository;

    @Override
    public ReportChemistryResponse createReportChemistry(ReportChemistryDto reportChemistryDto)
            throws ReportTypeNotFoundException, ChemistryNotFoundException, CanNotCreateChemistryException {

        // Validate the report type
        ReportType reportType = reportTypeRepository.findById(reportChemistryDto.getReportId()).orElseThrow(
                () -> new ReportTypeNotFoundException("That report type is not in the database")
        );

        // Validate and process the list of chemistry IDs
        List<Long> chemistryIds = reportChemistryDto.getChemistryIds();
        List<Long> addedChemistryIds = new ArrayList<>();

        for (Long chemistryId : chemistryIds) {
            Chemistry chemistry = chemistryRepository.findById(chemistryId).orElseThrow(
                    () -> new ChemistryNotFoundException("That chemistry is not in the database")
            );

            boolean exists = !reportChemistryRepository
                    .findReportChemistriesByChemistryIdAndReportId(chemistryId, reportChemistryDto.getReportId())
                    .isEmpty();

            if (exists) {
                throw new CanNotCreateChemistryException("Chemistry ID " + chemistryId + " is already linked to the report.");
            }

            ReportChemistry reportChemistry = new ReportChemistry();
            reportChemistry.setReportId(reportChemistryDto.getReportId());
            reportChemistry.setChemistryId(chemistryId);
            reportChemistryRepository.save(reportChemistry);

            addedChemistryIds.add(chemistryId);
        }

        ReportChemistryResponse response = new ReportChemistryResponse();
        response.setReportId(reportChemistryDto.getReportId());
        response.setChemistryIds(addedChemistryIds);

        return response;
    }
}
