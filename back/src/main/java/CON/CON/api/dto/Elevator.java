package CON.CON.api.dto;
/*{
            "경도(WGS84)": "127.4586979",
            "내외": "외",
            "설치위치": "1,2번출구측",
            "역사": "판암역",
            "운행구간(상부)": "지상",
            "운행구간(하부)": "지하1층",
            "위도(WGS84)": "36.3922577",
            "호기": 1
        }
        */

import com.fasterxml.jackson.annotation.JsonProperty;

public record Elevator(
        @JsonProperty("경도(WGS84)")
        String longitude,
        @JsonProperty("내외")
        String inout,
        @JsonProperty("설치위치")
        String location,
        @JsonProperty("역사")
        String station,
        @JsonProperty("운행구간(상부)")
        String upStation,
        @JsonProperty("운행구간(하부)")
        String downStation,
        @JsonProperty("위도(WGS84)")
        String latitude,
        @JsonProperty("호기")
        String unit
) {
}
