package CON.CON.api.controller;

import CON.CON.api.dto.StationCongestionDTO;
import CON.CON.api.dto.TimeAvgTotal;
import CON.CON.api.dto.TimeRecord;
import CON.CON.api.model.CongestionRecord;
import CON.CON.api.dto.SubwayData;
import CON.CON.api.service.ApiService;
import CON.CON.api.service.CongestionService;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CongestionController {

    private final ApiService apiService;
    private final CongestionService congestionService;

    public CongestionController(ApiService apiService, CongestionService congestionService) {
        this.apiService = apiService;
        this.congestionService = congestionService;
    }

    @GetMapping("/congestion/update")
    public ResponseEntity<SubwayData> updateCongestion(@RequestParam int page, @RequestParam int perPage) {
        ResponseEntity<String> response = apiService.requestCongestion(page, perPage);
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            SubwayData subwayData = objectMapper.readValue(responseBody, SubwayData.class);
            log.info("subway Data = {}",subwayData);
            List<CongestionRecord> congestionRecords = congestionService.saveCongestion(subwayData);
            log.info("congestions = {}",congestionRecords);
            return ResponseEntity.ok().body(subwayData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }   // 한달 사이에 승하차 인원 테이블 갱신

    @GetMapping("/congestion")
    public ResponseEntity<List<StationCongestionDTO>> getCongestion(@RequestParam String stationName) {
        //역별로, 시간대
        log.info(stationName);
        return ResponseEntity.ok().body(congestionService.findAverageCongestionByStationName(stationName));
    }     // 혼잡도 통계 승차

    @PostMapping("/congestion")
    public ResponseEntity<TimeAvgTotal> getCongestionByStation(@RequestBody TimeRecord timeRecord) {
        TimeRecord averageDown = congestionService.findAverageDown(timeRecord.getStationName(), timeRecord.getTime());
        TimeRecord averageByStationNameAndHour = congestionService.findAverageByStationNameAndHour(timeRecord.getStationName(), timeRecord.getTime());
        TimeAvgTotal timeAvgTotal = new TimeAvgTotal(averageByStationNameAndHour.getAverage(), averageDown.getAverage());
        return ResponseEntity.ok().body(timeAvgTotal);
    }
}
