package services;

import onlineservices.models.ClimatizationReport;
import models.ClimatizationReportEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repositories.ClimatizationReportRepository;

import java.util.List;

@Service
public class ClimatizationReportService {
    @Autowired
    private ClimatizationReportRepository climatizationReportRepository;

    public ClimatizationReportEntity saveClimatizationReportEntity(ClimatizationReport entity) {
        ClimatizationReportEntity climatizationReportEntity = new ClimatizationReportEntity();
        climatizationReportEntity.setDate(entity.getDate());
        climatizationReportEntity.setPower(entity.getPower());
        climatizationReportEntity.setActionCode(entity.getActionCode());
        return climatizationReportRepository.save(climatizationReportEntity);
    }

    public ClimatizationReportEntity getLastClimatizationReport() {
        Pageable pageable = PageRequest.of(0, 1);
        List<ClimatizationReportEntity> reports = climatizationReportRepository.findLastClimatizationReport(pageable);

        if (reports.isEmpty()) {
            return null;
        }

        ClimatizationReportEntity reportEntity = reports.get(0);
        ClimatizationReportEntity report = new ClimatizationReportEntity();
        report.setId(reportEntity.getId());
        report.setDate(reportEntity.getDate());
        report.setPower(reportEntity.getPower());
        report.setActionCode(reportEntity.getActionCode());
        return report;
    }
}
