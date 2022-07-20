package com.example.mobilkompaniya.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Raqamlar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private DavlatKodi davlatKodi;
    @ManyToOne()
    private Kodi kodi;
    @Column
    private String telraqam;
    @ManyToOne
    private ShaxsTuri shaxsTuri;
    @ManyToOne
    private Tariflar tariflar;
}
