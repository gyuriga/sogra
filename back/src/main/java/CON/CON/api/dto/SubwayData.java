package CON.CON.api.dto;

import java.util.List;
import lombok.Data;

@Data
public class SubwayData {
    private int currentCount;
    private List<SubwayRecord> data;
    private int matchCount;
    private int page;
    private int perPage;
    private int totalCount;
}