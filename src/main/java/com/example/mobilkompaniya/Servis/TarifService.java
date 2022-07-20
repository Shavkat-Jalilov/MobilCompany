package com.example.mobilkompaniya.Servis;

import com.example.mobilkompaniya.DTO.ApiResponse;
import com.example.mobilkompaniya.DTO.TariflarDTO;
import com.example.mobilkompaniya.Model.ShaxsTuri;
import com.example.mobilkompaniya.Model.Tariflar;
import com.example.mobilkompaniya.Model.Xodimlar;
import com.example.mobilkompaniya.Repository.RollRepository;
import com.example.mobilkompaniya.Repository.ShaxsRepository;
import com.example.mobilkompaniya.Repository.TarifRepository;
import com.example.mobilkompaniya.Repository.XodimRepository;
import com.example.mobilkompaniya.Roles.Rolles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TarifService {
    @Autowired
    TarifRepository tarifRepository;
    @Autowired
    RollRepository rollRepository;
    @Autowired
    XodimRepository xodimRepository;
    @Autowired
    ShaxsRepository shaxsRepository;
    public ApiResponse creatTarif(TariflarDTO tariflarDTO){
        Optional<Tariflar> byNomi = tarifRepository.findByNomi(tariflarDTO.getNomi());
        Optional<Xodimlar> byId = xodimRepository.findById(tariflarDTO.getXodimId());
        Optional<ShaxsTuri> shaxsTuri = shaxsRepository.findById(tariflarDTO.getShaxsTuri());
        if(byId.isPresent()){
            if (byId.get().getRollar().getRoleNomi().equals(Rolles.TARIF_MANAGER)){
                if (!byNomi.isPresent()){
                    Tariflar tariflar=new Tariflar();
                    tariflar.setNomi(tariflarDTO.getNomi());
                    tariflar.setShaxsTuri(shaxsTuri.get());
                    tariflar.setTarifNarxi(tariflarDTO.getTarifNarxi());
                    tariflar.setUtishNarxi(tariflarDTO.getUtishNarxi());
                    tariflar.setIchkiDaqiqa(tariflarDTO.getIchkiDaqiqa());
                    tariflar.setTashqiDaqiqa(tariflarDTO.getTashqiDaqiqa());
                    tariflar.setMegaBayt(tariflarDTO.getMegabayt());
                    tariflar.setMuddati(tariflarDTO.getMuddati());
                    tariflar.setSms(tariflarDTO.getSms());
                    tarifRepository.save(tariflar);
                    return new ApiResponse("Tarif muavffaqiyatli qo'shildi.", true);
                }
                return new ApiResponse("Bunday tarif mavjud.", false);
            }
            return new ApiResponse("Tarif qo'shish ruxsat berilmagan.", false);
        }
        return new ApiResponse("Bunday xodim mavjud emas.", false);
    }

}
