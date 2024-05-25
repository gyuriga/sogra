package CON.CON.api.repository;


import CON.CON.api.dto.StationCongestionDTO;
import CON.CON.api.model.CongestionRecord;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubwayRepository extends JpaRepository<CongestionRecord, Long> {
        @Query(
                "SELECT new CON.CON.api.dto.StationCongestionDTO(" +
                        "c.stationName, " +
                        "AVG(c.hour00_01), AVG(c.hour01_02), AVG(c.hour02_03), AVG(c.hour03_04), " +
                        "AVG(c.hour04_05), AVG(c.hour05_06), AVG(c.hour06_07), AVG(c.hour07_08), " +
                        "AVG(c.hour08_09), AVG(c.hour09_10), AVG(c.hour10_11), AVG(c.hour11_12), " +
                        "AVG(c.hour12_13), AVG(c.hour13_14), AVG(c.hour14_15), AVG(c.hour15_16), " +
                        "AVG(c.hour16_17), AVG(c.hour17_18), AVG(c.hour18_19), AVG(c.hour19_20), " +
                        "AVG(c.hour20_21), AVG(c.hour21_22), AVG(c.hour22_23), AVG(c.hour23_00)) " +
                        "FROM CongestionRecord c " +
                        "where c.type = '승차'"+
                        "GROUP BY c.stationName")
        List<StationCongestionDTO> findAverageCongestionByStationName(@Param("stationName") String stationName);

        @Query(
                "SELECT new CON.CON.api.dto.StationCongestionDTO(" +
                        "c.stationName, " +
                        "AVG(c.hour00_01), AVG(c.hour01_02), AVG(c.hour02_03), AVG(c.hour03_04), " +
                        "AVG(c.hour04_05), AVG(c.hour05_06), AVG(c.hour06_07), AVG(c.hour07_08), " +
                        "AVG(c.hour08_09), AVG(c.hour09_10), AVG(c.hour10_11), AVG(c.hour11_12), " +
                        "AVG(c.hour12_13), AVG(c.hour13_14), AVG(c.hour14_15), AVG(c.hour15_16), " +
                        "AVG(c.hour16_17), AVG(c.hour17_18), AVG(c.hour18_19), AVG(c.hour19_20), " +
                        "AVG(c.hour20_21), AVG(c.hour21_22), AVG(c.hour22_23), AVG(c.hour23_00)) " +
                        "FROM CongestionRecord c " +
                        "where c.type = '하차'"+
                        "GROUP BY c.stationName")
        List<StationCongestionDTO> findCongestionByStationName(@Param("stationName") String stationName);

        @Query(value = "SELECT AVG(" +
                "CASE "
                + "WHEN :fieldName = 'hour00_01' THEN hour00_01 " +
                "WHEN :fieldName = 'hour01_02' THEN hour01_02 " +
                "WHEN :fieldName = 'hour02_03' THEN hour02_03 " +
                "WHEN :fieldName = 'hour03_04' THEN hour03_04 " +
                "WHEN :fieldName = 'hour04_05' THEN hour04_05 " +
                "WHEN :fieldName = 'hour05_06' THEN hour05_06 " +
                "WHEN :fieldName = 'hour06_07' THEN hour06_07 " +
                "WHEN :fieldName = 'hour07_08' THEN hour07_08 " +
                "WHEN :fieldName = 'hour08_09' THEN hour08_09 " +
                "WHEN :fieldName = 'hour09_10' THEN hour09_10 " +
                "WHEN :fieldName = 'hour10_11' THEN hour10_11 " +
                "WHEN :fieldName = 'hour11_12' THEN hour11_12 " +
                "WHEN :fieldName = 'hour12_13' THEN hour12_13 " +
                "WHEN :fieldName = 'hour13_14' THEN hour13_14 " +
                "WHEN :fieldName = 'hour14_15' THEN hour14_15 " +
                "WHEN :fieldName = 'hour15_16' THEN hour15_16 " +
                "WHEN :fieldName = 'hour16_17' THEN hour16_17 " +
                "WHEN :fieldName = 'hour17_18' THEN hour17_18 " +
                "WHEN :fieldName = 'hour18_19' THEN hour18_19 " +
                "WHEN :fieldName = 'hour19_20' THEN hour19_20 " +
                "WHEN :fieldName = 'hour20_21' THEN hour20_21 " +
                "WHEN :fieldName = 'hour21_22' THEN hour21_22 " +
                "WHEN :fieldName = 'hour22_23' THEN hour22_23 " +
                "WHEN :fieldName = 'hour23_00' THEN hour23_00 END) " +
                "FROM CongestionRecord WHERE stationName = :stationName AND type = '승차'")
        Double findAverageByStationNameAndField(@Param("stationName") String stationName, @Param("fieldName") String fieldName);
}
