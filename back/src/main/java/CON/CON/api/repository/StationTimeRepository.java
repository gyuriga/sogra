package CON.CON.api.repository;

import CON.CON.api.model.StationTimes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationTimeRepository extends JpaRepository<StationTimes, Long> {
}
