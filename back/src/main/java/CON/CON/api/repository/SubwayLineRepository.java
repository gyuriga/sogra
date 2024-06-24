package CON.CON.api.repository;

import CON.CON.api.model.SubwayLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubwayLineRepository extends JpaRepository<SubwayLine, Long> {

}
