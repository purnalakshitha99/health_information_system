package lk.talentfort.health_information_system.service.impl;//package lk.talentfort.health_information_system.service.impl;
//
//import jakarta.persistence.EntityManager;
//import lk.talentfort.health_information_system.controller.dto.ReportColumnDto;
//import lk.talentfort.health_information_system.controller.response.ReportColumnResponse;
//import lk.talentfort.health_information_system.exception.ReportTypeNotFoundException;
//import lk.talentfort.health_information_system.model.ReportColumn;
//import lk.talentfort.health_information_system.model.ReportType;
//import lk.talentfort.health_information_system.repository.ReportColumnRepository;
//import lk.talentfort.health_information_system.repository.ReportTypeRepository;
//import lk.talentfort.health_information_system.service.ReportColumnService;
//import lombok.AllArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//
//@Service
//@AllArgsConstructor



//public class ReportColumnServiceImpl implements ReportColumnService {
//
//    private ModelMapper modelMapper;
//    private ReportColumnRepository reportColumnRepository;
//    private ReportTypeRepository reportTypeRepository;
//
//    public List<ReportColumnResponse> createReportColumn(Long reportId, List<ReportColumnDto> reportColumnDtoList)throws ReportTypeNotFoundException {
//
//        ReportType reportType = reportTypeRepository.findById(reportId).orElseThrow(
//                ()-> new ReportTypeNotFoundException("that Report type is not in a db")
//        );
//
//        List<ReportColumn> reportColumnList = reportColumnDtoList.stream().map(reportColumnDto -> {
//            ReportColumn reportColumn = modelMapper.map(reportColumnDto,ReportColumn.class);
//            reportColumn.setReportType(reportType);
//            return reportColumn;
//        }).toList();
//
//
//        reportColumnRepository.saveAll(reportColumnList);
//
//
//
//        return reportColumnList.stream().map(reportColumn -> modelMapper.map(reportColumn,ReportColumnResponse.class)).toList();
//
//
//    }
//
//}



//public class ReportColumnServiceImpl implements ReportColumnService {
//
//    private ModelMapper modelMapper;
//    private ReportColumnRepository reportColumnRepository;
//    private ReportTypeRepository reportTypeRepository;
//    private EntityManager entityManager;
//
//    @Override
//    public List<ReportColumnResponse> createReportColumn(Long reportId, List<ReportColumnDto> reportColumnDtoList) throws ReportTypeNotFoundException {
//
//        // Fetch the Report Type
//        ReportType reportType = reportTypeRepository.findById(reportId).orElseThrow(
//                () -> new ReportTypeNotFoundException("The specified Report Type is not in the database")
//        );
//
//        // Generate Dynamic Table Name
//        String tableName = reportType.getReportName().toLowerCase().replaceAll("\\s+", "_") + "_report";
//
//        // Generate the CREATE TABLE SQL Statement
//        StringBuilder createTableQuery = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + " (id BIGINT AUTO_INCREMENT PRIMARY KEY");
//
//        for (ReportColumnDto columnDto : reportColumnDtoList) {
//            createTableQuery.append(", ")
//                    .append(columnDto.getColumnName())
//                    .append(" ")
//                    .append(mapDataType(columnDto.getDataType()));
//        }
//        createTableQuery.append(");");
//
//        // Execute the CREATE TABLE Query
//        entityManager.createNativeQuery(createTableQuery.toString()).executeUpdate();
//
//        // Save Columns to the Report Column Table
//        List<ReportColumn> reportColumnList = reportColumnDtoList.stream().map(columnDto -> {
//            ReportColumn reportColumn = modelMapper.map(columnDto, ReportColumn.class);
//            reportColumn.setReportType(reportType);
//            return reportColumn;
//        }).toList();
//
//        reportColumnRepository.saveAll(reportColumnList);
//
//        // Return the response
//        return reportColumnList.stream().map(reportColumn -> modelMapper.map(reportColumn, ReportColumnResponse.class)).toList();
//    }
//
//    // Utility Method to Map Data Types
//    private String mapDataType(String dataType) {
//        return switch (dataType.toLowerCase()) {
//            case "string" -> "VARCHAR(255)";
//            case "integer" -> "INT";
//            case "boolean" -> "BOOLEAN";
//            case "double" -> "DOUBLE";
//            case "date" -> "DATE";
//            default -> "VARCHAR(255)";
//        };
//    }
//}



import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lk.talentfort.health_information_system.controller.dto.ReportColumnDto;
import lk.talentfort.health_information_system.controller.response.ReportColumnResponse;
import lk.talentfort.health_information_system.exception.ReportTypeNotFoundException;
import lk.talentfort.health_information_system.model.ReportColumn;
import lk.talentfort.health_information_system.model.ReportType;
import lk.talentfort.health_information_system.repository.ReportColumnRepository;
import lk.talentfort.health_information_system.repository.ReportTypeRepository;
import lk.talentfort.health_information_system.service.ReportColumnService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReportColumnServiceImpl implements ReportColumnService {


    private ModelMapper modelMapper;

    private ReportColumnRepository reportColumnRepository;


    private ReportTypeRepository reportTypeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<ReportColumnResponse> createReportColumn(Long reportId, List<lk.talentfort.health_information_system.controller.dto.ReportColumnDto> reportColumnDtoList) throws ReportTypeNotFoundException {

        // Fetch the Report Type
        ReportType reportType = reportTypeRepository.findById(reportId)
                .orElseThrow(() -> new ReportTypeNotFoundException("The specified Report Type is not in the database"));

        // Generate Dynamic Table Name
        String tableName = reportType.getReportName().toLowerCase().replaceAll("\\s+", "_") + "_report";

        // Validate column names
        for (ReportColumnDto columnDto : reportColumnDtoList) {
            if (!isValidColumnName(columnDto.getColumnName())) {
                throw new IllegalArgumentException("Invalid column name: " + columnDto.getColumnName());
            }
        }

        // Generate the CREATE TABLE SQL Statement
        StringBuilder createTableQuery = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + " (id BIGINT AUTO_INCREMENT PRIMARY KEY");

        for (ReportColumnDto columnDto : reportColumnDtoList) {
            createTableQuery.append(", ")
                    .append(columnDto.getColumnName())
                    .append(" ")
                    .append(mapDataType(columnDto.getDataType()));
        }
        createTableQuery.append(");");

        // Execute the CREATE TABLE Query
        entityManager.createNativeQuery(createTableQuery.toString()).executeUpdate();

        // Save Columns to the Report Column Table
        List<ReportColumn> reportColumnList = reportColumnDtoList.stream().map(columnDto -> {
            ReportColumn reportColumn = modelMapper.map(columnDto, ReportColumn.class);
            reportColumn.setReportType(reportType);
            return reportColumn;
        }).toList();

        reportColumnRepository.saveAll(reportColumnList);

        // Return the response
        return reportColumnList.stream().map(reportColumn -> modelMapper.map(reportColumn, ReportColumnResponse.class)).toList();
    }

    // Utility Method to Map Data Types
    private String mapDataType(String dataType) {
        return switch (dataType.toLowerCase()) {
            case "string" -> "VARCHAR(255)";
            case "integer" -> "INT";
            case "boolean" -> "BOOLEAN";
            case "double" -> "DOUBLE";
            case "date" -> "DATE";
            default -> "VARCHAR(255)";
        };
    }

    // Utility Method to Validate Column Names
    private boolean isValidColumnName(String columnName) {
        return columnName != null && columnName.matches("^[a-zA-Z_][a-zA-Z0-9_]*$");
    }
}
