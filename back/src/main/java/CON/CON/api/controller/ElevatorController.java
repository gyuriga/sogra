package CON.CON.api.controller;

import CON.CON.api.dto.Elevator;
import CON.CON.api.service.ElevatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class ElevatorController {

    private final ElevatorService elevatorService;

    public ElevatorController(ElevatorService elevatorService) {
        this.elevatorService = elevatorService;
    }

    @GetMapping("/elevator")
    public ResponseEntity<Elevator> getElevatorInfo(@RequestParam int page, @RequestParam int perPage, String stationName) {
        Elevator elevatorInfo = elevatorService.getElevatorInfo(page, perPage, stationName);
        return ResponseEntity.ok().body(elevatorInfo);
    }
}
