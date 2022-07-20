package com.example.mobilkompaniya.Controller;

import com.example.mobilkompaniya.DTO.*;
import com.example.mobilkompaniya.Servis.FilialServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filiallar")
public class FilialController {
    @Autowired
    FilialServis filialServis;
    @PostMapping("/creatFilial")
    public HttpEntity<?> creatFilial(@RequestBody FilialDTO filialDTO){
        ApiResponse apiResponse = filialServis.creatFilial(filialDTO);
        return ResponseEntity.status(apiResponse.getType()?200:409).body(apiResponse.getMessage());
    }
    @PostMapping("/creatFilialDirector")
    public HttpEntity<?> creatFilialDirector(@RequestBody FilialXodimDTO filialXodimDTO){
        ApiResponse apiResponse = filialServis.creatFilialDirector(filialXodimDTO);
        return ResponseEntity.status(apiResponse.getType()?200:409).body(apiResponse.getMessage());
    }

    @PostMapping("/creatManager")
    public HttpEntity<?> creatmanager(@RequestBody FilialXodimDTO filialXodimDTO){
        ApiResponse apiResponse = filialServis.creatManager(filialXodimDTO);
        return ResponseEntity.status(apiResponse.getType()?200:409).body(apiResponse.getMessage());
    }
    @PostMapping("/creatUser")
    public HttpEntity<?> creatUser(@RequestBody FilialXodimDTO filialXodimDTO){
        ApiResponse apiResponse = filialServis.creatUser(filialXodimDTO);
        return ResponseEntity.status(apiResponse.getType()?201:409).body(apiResponse.getMessage());
    }
    @GetMapping("/getFilial")
    public HttpEntity<?> getFilial(@RequestBody FilialXodimKurishDTO filialXodimKurishDTO){
        return ResponseEntity.ok(filialServis.getFilialXodim(filialXodimKurishDTO));
    }
}
