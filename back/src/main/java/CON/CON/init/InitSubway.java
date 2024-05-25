package CON.CON.init;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
//    @PostConstruct
    public void init() throws JsonProcessingException {
        String url = "http://www.djtc.kr/OpenAPI/service/TimeTableSVC/getAllTimeTable";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", serviceKey); // 헤더에 인증 키 설정

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


    }
}
