package CON.CON.api.dto;

import java.util.List;


public record SubwayTimeTable(
        int dayType,
        int drctType,
        int stNum,
        List<Integer> tmList,
        int tmZone
) {
}
