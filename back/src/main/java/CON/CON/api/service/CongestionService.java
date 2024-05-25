package CON.CON.api.service;

import CON.CON.api.dto.StationCongestionDTO;
import CON.CON.api.dto.TimeRecord;
import CON.CON.api.model.CongestionRecord;
import CON.CON.api.dto.SubwayData;
import CON.CON.api.dto.SubwayRecord;
import CON.CON.api.repository.SubwayRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CongestionService {

    private final SubwayRepository repository;

    public CongestionService(SubwayRepository repository) {
        this.repository = repository;
    }

    public List<CongestionRecord> saveCongestion(SubwayData subwayData) {
        List<SubwayRecord> data = subwayData.getData();
        return data.stream()
                .map(record -> repository.save(CongestionRecord.builder()
                        .hour00_01(record.getHour00_01())
                        .hour01_02(record.getHour01_02())
                        .hour02_03(record.getHour02_03())
                        .hour03_04(record.getHour03_04())
                        .hour04_05(record.getHour04_05())
                        .hour05_06(record.getHour05_06())
                        .hour06_07(record.getHour06_07())
                        .hour07_08(record.getHour07_08())
                        .hour08_09(record.getHour08_09())
                        .hour09_10(record.getHour09_10())
                        .hour10_11(record.getHour10_11())
                        .hour11_12(record.getHour11_12())
                        .hour12_13(record.getHour12_13())
                        .hour13_14(record.getHour13_14())
                        .hour14_15(record.getHour14_15())
                        .hour15_16(record.getHour15_16())
                        .hour16_17(record.getHour16_17())
                        .hour17_18(record.getHour17_18())
                        .hour18_19(record.getHour18_19())
                        .hour19_20(record.getHour19_20())
                        .hour20_21(record.getHour20_21())
                        .hour21_22(record.getHour21_22())
                        .hour22_23(record.getHour22_23())
                        .hour23_00(record.getHour23_00())
                        .type(record.getType())
                        .date(record.getDate())
                        .stationNumber(record.getStationNumber())
                        .stationName(record.getStationName())
                        .build())).toList();
    }

    public List<StationCongestionDTO> findAverageCongestionByStationName(String stationName) {
        List<StationCongestionDTO> averageCongestionByStationName = repository.findAverageCongestionByStationName(
                stationName);
        return averageCongestionByStationName;
    }

    public TimeRecord findAverageByStationNameAndHour(String stationName, String hourField) {
        return TimeRecord.builder().average((repository.findAverageByStationNameAndField(stationName, hourField))).build();
    }

    public List<StationCongestionDTO> findCongestionByStationName(String stationName) {
        List<StationCongestionDTO> congestionByStationName = repository.findCongestionByStationName(stationName);
        return congestionByStationName;
    }

    public TimeRecord findAverageDown(String stationName, String hourField) {
        return TimeRecord.builder().average(repository.findAverageDown(stationName, hourField)).build();
    }
}
