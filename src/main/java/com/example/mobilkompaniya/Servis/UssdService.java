package com.example.mobilkompaniya.Servis;

import com.example.mobilkompaniya.DTO.ApiResponse;
import com.example.mobilkompaniya.DTO.KodlarDTO;
import com.example.mobilkompaniya.Model.USSD;
import com.example.mobilkompaniya.Model.Xodimlar;
import com.example.mobilkompaniya.Repository.USSDRepository;
import com.example.mobilkompaniya.Repository.XodimRepository;
import com.example.mobilkompaniya.Roles.Rolles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class UssdService {
    @Autowired
    USSDRepository ussdRepository;
    @Autowired
    XodimRepository xodimRepository;

    public ApiResponse creatUSSD(@RequestBody KodlarDTO kodlarDTO){
        Optional<Xodimlar> optionalXodimlar = xodimRepository.findById(kodlarDTO.getXodimId());
        Optional<USSD> optionalUSSD = ussdRepository.findByUssdNomiAndUssdRaqam(kodlarDTO.getUsssdNomi(), kodlarDTO.getUssdRaqam());
        if (optionalUSSD.isPresent()){
            return new ApiResponse("Bunday USSD kod mavjud.", false);
        }
        if (optionalXodimlar.isPresent()){
            if (optionalXodimlar.get().getRollar().getRoleNomi().equals(Rolles.RAQAM_MANAGER)){
                USSD ussd=new USSD();
                ussd.setUssdNomi(kodlarDTO.getUsssdNomi());
                ussd.setUssdRaqam(kodlarDTO.getUssdRaqam());
                ussdRepository.save(ussd);
                return new ApiResponse("Ushbu USSD kod USSD kodlar ro'yxatiga muavffaqiyatli qo'shildi", true);
            }
            return new ApiResponse("USSD kod qo'shishga ruxsat berilmagan", false);
        }
        return new ApiResponse("Bunday xodim mavjud emas", false);
    }
}
