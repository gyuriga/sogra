package CON.CON.api.service;

import CON.CON.api.dto.Elevator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ElevatorService {

    private final ApiService apiService;

    public ElevatorService(ApiService apiService) {
        this.apiService = apiService;
    }

    public Elevator getElevatorInfo(int page, int perPage, String stationName) {
        ResponseEntity<String> response = apiService.requestElevator(page, perPage);
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        Elevator tmp = null;
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode dataNode = rootNode.path("data");

            if(dataNode.isArray()) {
                List<Elevator> elevators = new ArrayList<>();
                for (JsonNode node : (ArrayNode) dataNode) {
                    Elevator elevator = objectMapper.treeToValue(node, Elevator.class);
                    elevators.add(elevator);
                }
                tmp = elevators.get(0);
                for (Elevator elevator : elevators) {
                    if (elevator.station().equals(stationName)){
                        return elevator;
                    }
                }
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return tmp;
    }
}
