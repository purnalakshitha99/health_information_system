package lk.talentfort.health_information_system.repository;

import lk.talentfort.health_information_system.model.ReportChemistry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReportChemistryRepository extends JpaRepository<ReportChemistry,Long> {

    List<ReportChemistry> findReportChemistriesByChemistryIdAndReportId(Long chemistryId, Long reportId);
}
