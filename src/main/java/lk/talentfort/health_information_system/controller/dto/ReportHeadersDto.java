package lk.talentfort.health_information_system.controller.dto;


import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class ReportHeadersDto {

    private List<String> headers; // List of column headers
    private List<Map<String, Object>> data; // List of rows (dynamic data)


}
