package CON.CON.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ToiletDto{
    @JsonProperty("출구번호")
    private int exitNumber;

    @JsonProperty("지상지하구분")
    private String groundUndergroundDivision;

    @JsonProperty("게이트내외")
    private String gateInsideOutside;

    @JsonProperty("선명")
    private String lineName;

    @JsonProperty("역명")
    private String stationName;

    @JsonProperty("상세위치")
    private String detailedLocation;

    @JsonProperty("역층")
    private int stationFloor;

    @JsonProperty("철도운영기관명")
    private String os;

}
