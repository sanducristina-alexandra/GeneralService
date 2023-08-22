package repositories;

import models.ClimatizationReportEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClimatizationReportRepository extends JpaRepository<ClimatizationReportEntity, Integer> {
    @Query("SELECT cr FROM ClimatizationReportEntity cr ORDER BY cr.id DESC")
    List<ClimatizationReportEntity> findLastClimatizationReport(Pageable pageable);
}
