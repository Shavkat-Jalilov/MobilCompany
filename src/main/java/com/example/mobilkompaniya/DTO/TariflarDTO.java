package com.example.mobilkompaniya.DTO;

import lombok.Data;
import org.springframework.boot.convert.DataSizeUnit;

@Data
public class TariflarDTO {
    private Integer xodimId;
    private Integer roleType;
    private String nomi;
    private Integer shaxsTuri;
    private String tarifNarxi;
    private String utishNarxi;
    private String muddati;
    private String megabayt;
    private String ichkiDaqiqa;
    private String tashqiDaqiqa;
    private String sms;
}
