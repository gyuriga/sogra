package CON.CON.api.controller;

import CON.CON.api.service.SubwayLineService;
import java.io.IOException;

import java.net.URISyntaxException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SubwayLineController {

    private final SubwayLineService subwayLineService;

    public SubwayLineController(SubwayLineService subwayLineService) {
        this.subwayLineService = subwayLineService;
    }

    @GetMapping("/subway")
    public ResponseEntity<String> getSubway() throws IOException, URISyntaxException {
        return subwayLineService.saveSubwayLine();
    }   // 지하철 노선 시간 저장
}
