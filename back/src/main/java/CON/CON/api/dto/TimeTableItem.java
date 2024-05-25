package CON.CON.api.dto;

import lombok.Data;

@Data
public class TimeTableItem {
    private int dayType;
    private int drctType;
    private int stNum;
    private String tmList;
    private int tmZone;
}
