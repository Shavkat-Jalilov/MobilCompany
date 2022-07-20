package com.example.mobilkompaniya.Servis;

import com.example.mobilkompaniya.DTO.*;
import com.example.mobilkompaniya.Model.Filiallar;
import com.example.mobilkompaniya.Model.Rollar;
import com.example.mobilkompaniya.Model.Xodimlar;
import com.example.mobilkompaniya.Repository.FilialRepository;
import com.example.mobilkompaniya.Repository.RollRepository;
import com.example.mobilkompaniya.Repository.XodimRepository;
import com.example.mobilkompaniya.Roles.Rolles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilialServis {
    @Autowired
    RollRepository rollRepository;
    @Autowired
    XodimRepository xodimRepository;
    @Autowired
    FilialRepository filialRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public ApiResponse creatFilial(FilialDTO filialDTO){
        Optional<Xodimlar> byUsername = xodimRepository.findByUsername(filialDTO.getUsername());
        rollRepository.findById(filialDTO.getRoleTuri());
        if (byUsername.isPresent()){
            if(byUsername.get().getRollar().getRoleNomi().equals(Rolles.FILIAL_MANAGER)){
                Filiallar filiallar=new Filiallar();
                filiallar.setFilialNomi(filialDTO.getFilialNomi());
                filiallar.setManzil(filialDTO.getManzil());
                filialRepository.save(filiallar);
                return new ApiResponse("Filial muavffaqiyatli qo'shildi", true);
            }
            return new ApiResponse("Filial qo'shishga ruxsat yuq.", false);
        }
        return new ApiResponse("Username yoki parol mavjud emas !!!", false);
    }

    public ApiResponse creatFilialDirector(FilialXodimDTO xodimDTO){
        Optional<Xodimlar> optionalXodimlar = xodimRepository.findById(xodimDTO.getXodimId());
        Optional<Filiallar> optionalFiliallar = filialRepository.findById(xodimDTO.getFilialId());
        if (optionalXodimlar.isPresent()){
            if (optionalXodimlar.get().getRollar().getRoleNomi().equals(Rolles.FILIAL_MANAGER)){
                Xodimlar xodimlar=new Xodimlar();
                xodimlar.setFish(xodimDTO.getFish());
                xodimlar.setUsername(xodimDTO.getUsername());
                xodimlar.setPassword(passwordEncoder.encode(xodimDTO.getPassword()));
                xodimlar.setTelRaqam(xodimDTO.getTelRaqam());
                xodimlar.setRollar(rollRepository.findByRoleNomi(Rolles.FILIAL_RAXBAR));
                xodimlar.setFiliallar(optionalFiliallar.get());
                xodimRepository.save(xodimlar);
                return new ApiResponse("Xodim "+optionalFiliallar.get().getFilialNomi()+" filialga "+rollRepository.findById(xodimDTO.getRoleType()).get().getRoleNomi()+" lavozmiga muavffaqiyatli qo'shildi", true);
            }
            return new ApiResponse("Filial direktor  qo'shishga ruxsat berilmagan", false);
        }
        return new ApiResponse("Bunday xodim mavjud emas.", false);
    }

    public ApiResponse creatUser(FilialXodimDTO xodimDTO){
        Optional<Xodimlar> optionalXodimlar = xodimRepository.findById(xodimDTO.getXodimId());
        Optional<Filiallar> optionalFiliallar = filialRepository.findById(xodimDTO.getFilialId());
        if (optionalXodimlar.isPresent()){
            if (optionalXodimlar.get().getRollar().getRoleNomi().equals(Rolles.FILIAL_RAXBAR) || optionalXodimlar.get().getRollar().getRoleNomi().equals(Rolles.FILIAL_ICHKI_MANAGER)){
                if (optionalXodimlar.get().getFiliallar().equals(optionalFiliallar.get())){
                    Xodimlar xodimlar=new Xodimlar();
                    xodimlar.setFish(xodimDTO.getFish());
                    xodimlar.setUsername(xodimDTO.getUsername());
                    xodimlar.setPassword(passwordEncoder.encode(xodimDTO.getPassword()));
                    xodimlar.setTelRaqam(xodimDTO.getTelRaqam());
                    xodimlar.setRollar(rollRepository.findByRoleNomi(Rolles.USER));
                    xodimlar.setFiliallar(optionalFiliallar.get());
                    xodimRepository.save(xodimlar);
                    return new ApiResponse("Xodim "+optionalFiliallar.get().getFilialNomi()+"ga xodim  muavffaqiyatli qo'shildi", true);
                }
                return new ApiResponse("Siz faqat o'z filialingizga xodim qo'sha olasiz", false);
            }
            return new ApiResponse("Xodim qo'shishga ruxsat berilmagan", false);
        }
        return new ApiResponse("Bunday xodim mavjud emas.", false);
    }
    public ApiResponse creatManager(FilialXodimDTO  xodimDTO){
        Optional<Xodimlar> optionalXodimlar = xodimRepository.findById(xodimDTO.getXodimId());
        Optional<Filiallar> optionalFiliallar = filialRepository.findById(xodimDTO.getFilialId());
        if (optionalXodimlar.isPresent() ){
            if (optionalXodimlar.get().getRollar().getRoleNomi().equals(Rolles.FILIAL_RAXBAR)){
                if (optionalXodimlar.get().getFiliallar().equals(optionalFiliallar.get())){
                    Xodimlar xodimlar=new Xodimlar();
                    xodimlar.setFish(xodimDTO.getFish());
                    xodimlar.setUsername(xodimDTO.getUsername());
                    xodimlar.setPassword(passwordEncoder.encode(xodimDTO.getPassword()));
                    xodimlar.setTelRaqam(xodimDTO.getTelRaqam());
                    xodimlar.setRollar(rollRepository.findByRoleNomi(Rolles.FILIAL_ICHKI_MANAGER));
                    xodimlar.setFiliallar(optionalFiliallar.get());
                    xodimRepository.save(xodimlar);
                    return new ApiResponse("Xodim "+optionalFiliallar.get().getFilialNomi()+" filialga filial menejeri lavozimiga muavffaqiyatli qo'shildi", true);
               }
                return new ApiResponse("Siz faqat o'z filialingizga meneger qo'sha olasiz", false);
            }
            return new ApiResponse("Xodim qo'shishga ruxsat berilmagan", false);
        }
        return new ApiResponse("Bunday xodim mavjud emas.", false);
    }

    public List<Filiallar> getFilialXodim(FilialXodimKurishDTO filialXodimKurishDTO){
        Optional<Xodimlar> optionalXodimlar = xodimRepository.findById(filialXodimKurishDTO.getXodimID());
         if(optionalXodimlar.isPresent()){
            if (optionalXodimlar.get().getRollar().getRoleNomi().equals(Rolles.FILIAL_MANAGER)){
               return filialRepository.findAll();
            }
            return null;
        }
        return null;
    }




}
