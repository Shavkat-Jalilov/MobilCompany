package com.example.mobilkompaniya.Controller;

import com.example.mobilkompaniya.DTO.ApiResponse;
import com.example.mobilkompaniya.DTO.TariflarDTO;
import com.example.mobilkompaniya.Servis.TarifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tariflar")
public class TariflarController {
    @Autowired
    TarifService tarifService;
    @PostMapping("/creatTarif")
    public HttpEntity<?> creatTarif(@RequestBody TariflarDTO tariflarDTO){
        ApiResponse apiResponse = tarifService.creatTarif(tariflarDTO);
        return ResponseEntity.status(apiResponse.getType()?200:409).body(apiResponse.getMessage());
    }

}
