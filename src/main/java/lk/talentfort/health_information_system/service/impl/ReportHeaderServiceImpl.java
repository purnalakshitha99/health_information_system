package lk.talentfort.health_information_system.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lk.talentfort.health_information_system.controller.dto.ReportHeadersDto;
import lk.talentfort.health_information_system.model.ReportHeader;
import lk.talentfort.health_information_system.repository.ReportHeaderRepository;
import lk.talentfort.health_information_system.service.ReportHeaderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReportHeaderServiceImpl implements ReportHeaderService {

    private final ReportHeaderRepository reportHeaderRepository;
    private final ObjectMapper objectMapper;


    @Override
    public void saveReport(ReportHeadersDto reportHeadersDto) {

       try {
           String headersJson = objectMapper.writeValueAsString(reportHeadersDto.getHeaders());
           String dataJson = objectMapper.writeValueAsString(reportHeadersDto.getData());

           ReportHeader reportHeader = new ReportHeader();
           reportHeader.setHeaders(headersJson);
           reportHeader.setData(dataJson);

           reportHeaderRepository.save(reportHeader);
       } catch (JsonProcessingException e) {
           throw new RuntimeException("Error Processing JSON",e);
       }
    }
}
