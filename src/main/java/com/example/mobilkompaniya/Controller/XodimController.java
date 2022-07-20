package com.example.mobilkompaniya.Controller;

import com.example.mobilkompaniya.DTO.ApiResponse;
import com.example.mobilkompaniya.DTO.DirectorDTO;
import com.example.mobilkompaniya.DTO.LoginDTO;
import com.example.mobilkompaniya.DTO.XodimDTO;
import com.example.mobilkompaniya.Servis.XodimServis;
import org.hibernate.loader.plan.build.internal.LoadGraphLoadPlanBuildingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mobil")
public class XodimController {
    @Autowired
    XodimServis xodimServis;
    @PostMapping("/creatDirector")
    public HttpEntity<?> creatDirector(@RequestBody DirectorDTO directorDTO){
        ApiResponse apiResponse = xodimServis.creatDirector(directorDTO);
      return   ResponseEntity.status(apiResponse.getType()?200:409).body(apiResponse.getMessage());
    }
    @PostMapping("/creatManager")
    public HttpEntity<?> creatManagers(@RequestBody XodimDTO xodimDTO){
        ApiResponse apiResponse = xodimServis.creatManager(xodimDTO);
        return ResponseEntity.status(apiResponse.getType()?200:409).body(apiResponse.getMessage());
    }
    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDTO loginDTO){
        ApiResponse apiResponse = xodimServis.hodimLogin(loginDTO);
       return ResponseEntity.status(apiResponse.getType()?200:401).body(apiResponse.getMessage());
    }

}
