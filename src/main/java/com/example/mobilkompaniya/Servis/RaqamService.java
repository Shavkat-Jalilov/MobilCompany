package com.example.mobilkompaniya.Servis;

import com.example.mobilkompaniya.DTO.ApiResponse;
import com.example.mobilkompaniya.DTO.FoydalanuvchiDTO;
import com.example.mobilkompaniya.DTO.RaqamDTo;
import com.example.mobilkompaniya.DTO.UpdateTarifDTO;
import com.example.mobilkompaniya.Model.*;
import com.example.mobilkompaniya.Repository.*;
import com.example.mobilkompaniya.Roles.Rolles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RaqamService {
    @Autowired
    RaqamRepository raqamRepository;
    @Autowired
    RollRepository rollRepository;
    @Autowired
    XodimRepository xodimRepository;
    @Autowired
    KodRepository kodRepository;
    @Autowired
    DRaqamRepository dRaqamRepository;
    @Autowired
    ShaxsRepository shaxsRepository;
    @Autowired
    TarifRepository tarifRepository;
    @Autowired
    USSDRepository ussdRepository;

    public ApiResponse creatNumber(RaqamDTo raqamDTo){
        Optional<Kodi> kodiOptional = kodRepository.findById(raqamDTo.getRaqamCodeId());
        Optional<Raqamlar> raqamlarOptional = raqamRepository.findByKodiAndTelraqam(kodiOptional.get(), raqamDTo.getTelRaqam());
        Optional<Xodimlar> optionalXodimlar = xodimRepository.findById(raqamDTo.getXodimId());
        Optional<ShaxsTuri> shaxsTuri = shaxsRepository.findById(raqamDTo.getShTuri());
        Optional<DavlatKodi> davlatKodi = dRaqamRepository.findById(raqamDTo.getDcodeId());
        Optional<Tariflar> tariflarOptional = tarifRepository.findById(raqamDTo.getTarifId());
        rollRepository.findById(raqamDTo.getRoleTuri());
        if (optionalXodimlar.isPresent()){
            if (optionalXodimlar.get().getRollar().getRoleNomi().equals(Rolles.USER)){
                if (!raqamlarOptional.isPresent()){
                    if (tariflarOptional.isPresent()){
                        Raqamlar raqamlar=new Raqamlar();
                        raqamlar.setDavlatKodi(davlatKodi.get());
                        raqamlar.setKodi(kodiOptional.get());
                        raqamlar.setTelraqam(raqamDTo.getTelRaqam());
                        raqamlar.setShaxsTuri(shaxsTuri.get());
                        raqamlar.setTariflar(tariflarOptional.get());
                        raqamRepository.save(raqamlar);
                        String raqam=davlatKodi.get().getDavlatCode()+kodiOptional.get().getCode().toString()+raqamDTo.getTelRaqam();
                        return new ApiResponse(raqam+" raqami "+tariflarOptional.get().getNomi()+" tarif rejasiga muavffaqiyatli saqlandi", true);
                    }
                    return new ApiResponse("Bunday tarif mavjud emas", false);
                }
                return new ApiResponse("Bunday raqam mavjud.", false);
            }
            return new ApiResponse("Raqam qo'shishga ruxsat yo'q", false);
        }
        return new ApiResponse("Bunday xodim mavjud emas", false);
    }

    public ApiResponse aboutNumber(FoydalanuvchiDTO foydalanuvchiDTO){
        String raqamcode=foydalanuvchiDTO.getTelraqam().toString().substring(4,6);
        String raqam=foydalanuvchiDTO.getTelraqam().toString().substring(6,13);
        Optional<Kodi> byCode = kodRepository.findByCode(raqamcode);
        Optional<Raqamlar> kodiAndTelraqam = raqamRepository.findByKodiAndTelraqam(byCode.get(), raqam);
        if (kodiAndTelraqam.isPresent()){
            return  new ApiResponse("Sizning raqamingiz "+foydalanuvchiDTO.getTelraqam()+" \nTa'rifingiz xaqida ma'lumotlar "+kodiAndTelraqam.get().getTariflar(), true);
        }
        return new ApiResponse("Bunday raqam mavju emas", false);
    }
    public ApiResponse updateTarif(UpdateTarifDTO updateTarifDTO){
        String raqamcode=updateTarifDTO.getTelRaqam().substring(4,6);
        String raqam=updateTarifDTO.getTelRaqam().toString().substring(6,13);
        System.out.println(raqamcode+"\n\n"+raqam);
        Optional<Tariflar> tariflar = tarifRepository.findById(updateTarifDTO.getTarifId());
        Optional<Kodi> byCode = kodRepository.findByCode(raqamcode);
        Optional<Raqamlar> kodiAndTelraqam = raqamRepository.findByKodiAndTelraqam(byCode.get(), raqam);
        if (kodiAndTelraqam.isPresent()){
            if (!kodiAndTelraqam.get().getTariflar().equals(tariflar.get())){
                kodiAndTelraqam.get().setTariflar(tariflar.get());
                return new ApiResponse("Tarifingiz "+tariflar.get().getNomi()+" tarifiga muavffaqiyatli o'zgartirildi", true);
            }
            return new ApiResponse("Sizning raqamingiz xuddi shu tarifda", false);
        }
        return new ApiResponse("raqam mavjud emas",false);
    }
}
