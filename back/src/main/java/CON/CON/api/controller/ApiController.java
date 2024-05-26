package CON.CON.api.controller;

import CON.CON.api.dto.StationCongestionDTO;
import CON.CON.api.dto.TimeAvgTotal;
import CON.CON.api.dto.TimeRecord;
import CON.CON.api.dto.TimeTableItem;
import CON.CON.api.dto.ToiletDto;
import CON.CON.api.model.CongestionRecord;
import CON.CON.api.dto.SubwayData;
import CON.CON.api.service.CongestionService;
import CON.CON.api.service.TimeTableService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ApiController {

    @Value(value = "${congestionKey}")
    private String congestionKey;
    @Value(value = "${subwayKey}")
    private String subwayKey;
    @Value(value = "${toiletKey}")
    private String toiletKey;
    private final CongestionService congestionService;
    private final TimeTableService timeTableService;

    public ApiController(CongestionService congestionService, TimeTableService timeTableService) {
        this.congestionService = congestionService;
        this.timeTableService = timeTableService;
    }

    @GetMapping("/toilet")
    public ResponseEntity<ToiletDto> toilet(@RequestParam int page, @RequestParam int perPage, String stationName) {
        String url = "https://api.odcloud.kr/api/15041244/v1/uddi:34d0069b-5bff-43ec-a83d-d986eefdcb08?"
                +"page="+page+"&perPage="+perPage;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", toiletKey); // 헤더에 인증 키 설정

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        String responseBody = response.getBody();
        log.info("response Body = {}",responseBody);
        ObjectMapper objectMapper = new ObjectMapper();
        ToiletDto tmp = null;
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode dataNode = rootNode.path("data");

            if (dataNode.isArray()) {
                List<ToiletDto> toiletList = new ArrayList<>();
                for (JsonNode node : (ArrayNode) dataNode) {
                    ToiletDto toilet = objectMapper.treeToValue(node, ToiletDto.class);
                    toiletList.add(toilet);
                }
                tmp = toiletList.get(0);
                // 추출된 데이터 출력
                for (ToiletDto toilet : toiletList) {
                    log.info("toiletName = {}",toilet.getStationName());
                    if (toilet.getStationName().equals(stationName)){
                        return ResponseEntity.ok().body(toilet);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(tmp);
    }
    @GetMapping("/subway")
    public ResponseEntity<String> getSubway() throws IOException, URISyntaxException {
        StringBuilder urlBuilder = new StringBuilder("http://www.djtc.kr/OpenAPI/service/TimeTableSVC/getAllTimeTable"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+subwayKey); /*Service Key*/
        URL url = new URL(urlBuilder.toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                url.toURI(),
                HttpMethod.GET,
                entity,
                String.class
        );

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseBody);

        JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");
        List<TimeTableItem> timeTableItems = new ArrayList<>();

        if (itemsNode.isArray()) {
            for (JsonNode itemNode : itemsNode) {
                TimeTableItem item = objectMapper.treeToValue(itemNode, TimeTableItem.class);
                String cleanedTmList = cleanTmList(item.getTmList());
                item.setTmList(cleanedTmList);
//                log.info("itemList = {}", item.getTmList());
                timeTableItems.add(item);
            }
        }
        log.info("stNum = {}",timeTableItems.get(0).getStNum());
        timeTableService.saveTimeTable(timeTableItems);
        return response;
    }   // 지하철 노선 시간 저장

    private String cleanTmList(String tmList) {
        // Remove any text in parentheses and trim extra spaces
        return tmList.replaceAll("\\(.*?\\)", "").trim();
    }
    @GetMapping("/congestion/update")
    public ResponseEntity<SubwayData> updateCongestion(@RequestParam int page, @RequestParam int perPage) {

        String url = "https://api.odcloud.kr/api/15060591/v1/uddi:95ef39f5-da86-4916-bfad-89fb6f6953b4?"
                + "page="+page+"&perPage="+perPage;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", congestionKey); // 헤더에 인증 키 설정

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        String responseBody = response.getBody();
        log.info("response Body = {}",responseBody);
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

//    @GetMapping("/elevator")
//    public
}
