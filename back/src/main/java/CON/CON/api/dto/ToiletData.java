package CON.CON.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class ToiletData {
    @JsonProperty("currentCount")
    private int currentCount;
    @JsonProperty("Data")
    private List<ToiletDto> data;
    private int matchCount;
    private int page;
    private int perPage;
    private int totalCount;
}
