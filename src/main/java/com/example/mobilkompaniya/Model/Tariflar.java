package com.example.mobilkompaniya.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Tariflar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String nomi;
    @ManyToOne
    private ShaxsTuri shaxsTuri;
    @Column(nullable = false)
    private String TarifNarxi;
    @Column(nullable = false)
    private String UtishNarxi;
    @Column(nullable = false)
    private String muddati;
    @Column (nullable = false)
    private String MegaBayt;
    @Column(nullable = false)
    private String ichkiDaqiqa;
    @Column(nullable = false)
    private String tashqiDaqiqa;
    @Column(nullable = false)
    private String sms;


}
