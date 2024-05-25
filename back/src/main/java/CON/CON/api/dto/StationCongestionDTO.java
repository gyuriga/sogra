package CON.CON.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StationCongestionDTO {
    private String stationName;
    private double hour00_01;
    private double hour01_02;
    private double hour02_03;
    private double hour03_04;
    private double hour04_05;
    private double hour05_06;
    private double hour06_07;
    private double hour07_08;
    private double hour08_09;
    private double hour09_10;
    private double hour10_11;
    private double hour11_12;
    private double hour12_13;
    private double hour13_14;
    private double hour14_15;
    private double hour15_16;
    private double hour16_17;
    private double hour17_18;
    private double hour18_19;
    private double hour19_20;
    private double hour20_21;
    private double hour21_22;
    private double hour22_23;
    private double hour23_00;
}