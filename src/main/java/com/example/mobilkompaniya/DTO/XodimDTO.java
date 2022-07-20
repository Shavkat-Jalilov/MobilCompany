package com.example.mobilkompaniya.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class XodimDTO {
    private Integer xodimId;
    private String fish;
    private String telRaqam;
    private String username;
    private String password;
    private Integer roleType;
}
