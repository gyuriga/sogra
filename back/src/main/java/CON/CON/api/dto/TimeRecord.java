package CON.CON.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimeRecord {
    String stationName;
    String time;
    Double average;
}
