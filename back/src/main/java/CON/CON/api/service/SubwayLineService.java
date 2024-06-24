package CON.CON.api.service;

import CON.CON.api.dto.TimeTableItem;
import CON.CON.api.model.StationTimes;
import CON.CON.api.model.SubwayLine;
import CON.CON.api.repository.StationTimeRepository;
import CON.CON.api.repository.SubwayLineRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SubwayLineService {

    private final ApiService apiService;
    private final HashMap<Integer, String> stationName = new HashMap<>();
    private final SubwayLineRepository subwayLineRepository;
    private final StationTimeRepository stationTimeRepository;

    public SubwayLineService(ApiService apiService, SubwayLineRepository subwayLineRepository, StationTimeRepository stationTimeRepository) {
        this.apiService = apiService;
        this.subwayLineRepository = subwayLineRepository;
        this.stationTimeRepository = stationTimeRepository;
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

    public ResponseEntity<String> saveSubwayLine()
            throws UnsupportedEncodingException, JsonProcessingException, MalformedURLException, URISyntaxException {
        ResponseEntity<String> response = apiService.requestSubwayLine();
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
                timeTableItems.add(item);
            }
        }

        for (TimeTableItem timeTableItem : timeTableItems) {
            String[] split = timeTableItem.getTmList().split(" ");

            for (String s : split) {
                stationTimeRepository.save(
                        StationTimes.builder()
                                .time(Integer.parseInt(s))
                                .stationName(stationName.get(timeTableItem.getStNum()))
                                .build());
            }
            subwayLineRepository.save(
                    SubwayLine.builder()
                            .timeZone(timeTableItem.getTmZone())
                            .dayType(timeTableItem.getDayType())
                            .directionType(timeTableItem.getDrctType())
                            .build()
            );
        }
        return response;
    }

    private String cleanTmList(String tmList) {
        // Remove any text in parentheses and trim extra spaces
        return tmList.replaceAll("\\(.*?\\)", "").trim();
    }
}
