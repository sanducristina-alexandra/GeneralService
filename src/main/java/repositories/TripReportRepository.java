package repositories;

import models.TripReportEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripReportRepository extends JpaRepository<TripReportEntity, Integer> {
    @Query("SELECT tr FROM TripReportEntity tr WHERE tr.status = 'COMPLETE' ORDER BY tr.id DESC")
    List<TripReportEntity> findLastCompletedTrip(Pageable pageable);

    @Query("SELECT tr FROM TripReportEntity tr ORDER BY tr.id DESC")
    List<TripReportEntity> findLastTripReport(Pageable pageable);
}
