package CON.CON.init;

import CON.CON.api.controller.CongestionController;
import CON.CON.api.service.ApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitSubway {

    @Value(value = "${subwayKey}")
    String serviceKey;

    private final CongestionController congestionController;

    @PostConstruct
    public void init() throws JsonProcessingException {
        congestionController.updateCongestion(1,44);
    }
}
