package CON.CON.api.service;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@Component
public class ApiService {

    @Value(value = "${congestionKey}")
    private String congestionKey;
    @Value(value = "${subwayKey}")
    private String subwayKey;
    @Value(value = "${toiletKey}")
    private String toiletKey;
    @Value(value = "${elevatorKey}")
    private String elevatorKey;

    public static ResponseEntity<String> requestApi(String url, String congestionKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", congestionKey); // 헤더에 인증 키 설정

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );
    }

    public ResponseEntity<String> requestCongestion(int page, int perPage) {
        String url = "https://api.odcloud.kr/api/15060591/v1/uddi:95ef39f5-da86-4916-bfad-89fb6f6953b4?"
                + "page="+page+"&perPage="+perPage;

        return requestApi(url, congestionKey);
    }

    public ResponseEntity<String> requestToilet(int page, int perPage) {
        String url = "https://api.odcloud.kr/api/15041244/v1/uddi:34d0069b-5bff-43ec-a83d-d986eefdcb08?"
                + "page=" + page + "&perPage=" + perPage;
        return requestApi(url, toiletKey);
    }

    public ResponseEntity<String> requestElevator(int page, int perPage) {
        String url = "http://api.odcloud.kr/api/15043922/v1/uddi:455dfa45-641d-4829-941d-97a2d27f7443?"
                + "page=" + page + "&perPage=" + perPage;
        return requestApi(url, elevatorKey);
    }

    public ResponseEntity<String> requestSubwayLine()
            throws UnsupportedEncodingException, MalformedURLException, URISyntaxException {
        String url = "http://www.djtc.kr/OpenAPI/service/TimeTableSVC/getAllTimeTable?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + subwayKey; /*URL*/
        URL u = new URL(url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(
                u.toURI(),
                HttpMethod.GET,
                entity,
                String.class
        );
    }
}
