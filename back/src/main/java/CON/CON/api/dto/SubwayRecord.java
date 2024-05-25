package CON.CON.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SubwayRecord {
    @JsonProperty("00-01시")
    private int hour00_01;
    @JsonProperty("01-02시")
    private int hour01_02;
    @JsonProperty("02-03시")
    private int hour02_03;
    @JsonProperty("03-04시")
    private int hour03_04;
    @JsonProperty("04-05시")
    private int hour04_05;
    @JsonProperty("05-06시")
    private int hour05_06;
    @JsonProperty("06-07시")
    private int hour06_07;
    @JsonProperty("07-08시")
    private int hour07_08;
    @JsonProperty("08-09시")
    private int hour08_09;
    @JsonProperty("09-10시")
    private int hour09_10;
    @JsonProperty("10-11시")
    private int hour10_11;
    @JsonProperty("11-12시")
    private int hour11_12;
    @JsonProperty("12-13시")
    private int hour12_13;
    @JsonProperty("13-14시")
    private int hour13_14;
    @JsonProperty("14-15시")
    private int hour14_15;
    @JsonProperty("15-16시")
    private int hour15_16;
    @JsonProperty("16-17시")
    private int hour16_17;
    @JsonProperty("17-18시")
    private int hour17_18;
    @JsonProperty("18-19시")
    private int hour18_19;
    @JsonProperty("19-20시")
    private int hour19_20;
    @JsonProperty("20-21시")
    private int hour20_21;
    @JsonProperty("21-22시")
    private int hour21_22;
    @JsonProperty("22-23시")
    private int hour22_23;
    @JsonProperty("23-00시")
    private int hour23_00;
    @JsonProperty("구분")
    private String type;
    @JsonProperty("날짜")
    private String date;
    @JsonProperty("역명")
    private String stationName;
    @JsonProperty("역번호")
    private int stationNumber;
}
