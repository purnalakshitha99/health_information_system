package lk.talentfort.health_information_system.controller.request;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ReportHeadersRq {


    private List<String> headers;

    private List<Map<String, Object>> data;


}
