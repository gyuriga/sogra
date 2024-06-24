package CON.CON.api.service;

import CON.CON.api.dto.ToiletDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ToiletService {

    private final ApiService apiService;

    public ToiletService(ApiService apiService) {
        this.apiService = apiService;
    }

    public ToiletDto getToiletInfo(int page, int perPage, String stationName){
        ResponseEntity<String> response = apiService.requestToilet(page, perPage);
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
                        return toilet;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmp;
    }
}
