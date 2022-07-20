package com.example.mobilkompaniya.Controller;

import com.example.mobilkompaniya.DTO.ApiResponse;
import com.example.mobilkompaniya.DTO.KodlarDTO;
import com.example.mobilkompaniya.Servis.UssdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/USSD")
public class UssdController {
    @Autowired
    UssdService ussdService;
    @PostMapping("/craetUSSD")
    public HttpEntity<?> creatUSSD(@RequestBody KodlarDTO kodlarDTO){
        ApiResponse apiResponse = ussdService.creatUSSD(kodlarDTO);
        return ResponseEntity.status(apiResponse.getType()?200:409).body(apiResponse.getMessage());
    }
}
