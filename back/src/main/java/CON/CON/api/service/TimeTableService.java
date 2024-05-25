package CON.CON.api.service;

import CON.CON.api.dto.TimeTableItem;
import CON.CON.api.model.StationTimes;
import CON.CON.api.model.TimeTable;
import CON.CON.api.repository.StationTimeRepository;
import CON.CON.api.repository.TimeTableRepository;
import java.util.HashMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TimeTableService {

    private final HashMap<Integer, String> stationName = new HashMap<>();
    private TimeTableRepository timeRepository;
    private StationTimeRepository stationTimeRepository;
    public TimeTableService(TimeTableRepository timeRepository, StationTimeRepository stationTimeRepository) {
        this.timeRepository = timeRepository;
        this.stationTimeRepository = stationTimeRepository;
    }

    public TimeTableService() {
        stationName.put(101,"판암");
        stationName.put(102,"신흥");
        stationName.put(103,"대동");
        stationName.put(104,"대전");
        stationName.put(105,"중앙로");
        stationName.put(106,"중구청");
        stationName.put(107,"서대전네거리");
        stationName.put(108,"오룡");
        stationName.put(109,"용문");
        stationName.put(110,"탄방");
        stationName.put(111,"시청");
        stationName.put(112,"정부청사");
        stationName.put(113,"갈마");
        stationName.put(114,"월평");
        stationName.put(115,"갑천");
        stationName.put(116,"유성온천");
        stationName.put(117,"구암");
        stationName.put(118,"현충원");
        stationName.put(119,"월드컵경기장");
        stationName.put(120,"노은");
        stationName.put(121,"지족");
        stationName.put(122,"반석");
    }

    public void saveTimeTable(List<TimeTableItem> timeTableItems) {
        for (TimeTableItem timeTableItem : timeTableItems) {
            String[] split = timeTableItem.getTmList().split(" ");

            for (String s : split) {
                stationTimeRepository.save(
                        StationTimes.builder()
                                .time(Integer.parseInt(s))
                                .stationName(stationName.get(timeTableItem.getStNum()))
                                .build());
            }
            timeRepository.save(
                    TimeTable.builder()
                            .timeZone(timeTableItem.getTmZone())
                            .dayType(timeTableItem.getDayType())
                            .directionType(timeTableItem.getDrctType())
                            .build()
            );
        }
    }
}
