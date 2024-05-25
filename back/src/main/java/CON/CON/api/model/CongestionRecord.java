package CON.CON.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CongestionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String date;
    private String stationName;
    private int stationNumber;
    private int hour00_01;
    private int hour01_02;
    private int hour02_03;
    private int hour03_04;
    private int hour04_05;
    private int hour05_06;
    private int hour06_07;
    private int hour07_08;
    private int hour08_09;
    private int hour09_10;
    private int hour10_11;
    private int hour11_12;
    private int hour12_13;
    private int hour13_14;
    private int hour14_15;
    private int hour15_16;
    private int hour16_17;
    private int hour17_18;
    private int hour18_19;
    private int hour19_20;
    private int hour20_21;
    private int hour21_22;
    private int hour22_23;
    private int hour23_00;
}
