package CON.CON.api.controller;

import CON.CON.api.dto.ToiletDto;
import CON.CON.api.service.ToiletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class ToiletController {

    private final ToiletService toiletService;

    public ToiletController(ToiletService toiletService) {
        this.toiletService = toiletService;
    }

    @GetMapping("/toilet")
    public ResponseEntity<ToiletDto> toilet(@RequestParam int page, @RequestParam int perPage, String stationName) {
        ToiletDto toiletInfo = toiletService.getToiletInfo(page, perPage, stationName);
        return ResponseEntity.ok().body(toiletInfo);
    }
}
