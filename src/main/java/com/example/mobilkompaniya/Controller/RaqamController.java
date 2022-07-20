package com.example.mobilkompaniya.Controller;

import com.example.mobilkompaniya.DTO.ApiResponse;
import com.example.mobilkompaniya.DTO.FoydalanuvchiDTO;
import com.example.mobilkompaniya.DTO.RaqamDTo;
import com.example.mobilkompaniya.DTO.UpdateTarifDTO;
import com.example.mobilkompaniya.Servis.RaqamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/raqam")
public class RaqamController {
    @Autowired
    RaqamService raqamService;
    @PostMapping("/creatNumber")
    public HttpEntity<?> creatNumber(@RequestBody RaqamDTo raqamDTo){
        ApiResponse apiResponse = raqamService.creatNumber(raqamDTo);
        return ResponseEntity.status(apiResponse.getType()?200:408).body(apiResponse.getMessage());
    }
    @PostMapping("/aboutNumber")
    public HttpEntity<?> aboutNumber(@RequestBody FoydalanuvchiDTO foydalanuvchiDTO){
        ApiResponse apiResponse = raqamService.aboutNumber(foydalanuvchiDTO);
        return ResponseEntity.ok(apiResponse.getMessage());
    }
    @PostMapping("/updateTarif")
    public HttpEntity<?> updateTarif(@RequestBody UpdateTarifDTO updateTarifDTO){
        ApiResponse apiResponse = raqamService.updateTarif(updateTarifDTO);
        return ResponseEntity.ok(apiResponse.getMessage());
    }

}
