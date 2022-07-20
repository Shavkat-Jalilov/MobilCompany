package com.example.mobilkompaniya.Servis;

import com.example.mobilkompaniya.DTO.ApiResponse;
import com.example.mobilkompaniya.DTO.DirectorDTO;
import com.example.mobilkompaniya.DTO.LoginDTO;
import com.example.mobilkompaniya.DTO.XodimDTO;
import com.example.mobilkompaniya.Model.Rollar;
import com.example.mobilkompaniya.Model.Xodimlar;
import com.example.mobilkompaniya.Repository.RollRepository;
import com.example.mobilkompaniya.Repository.XodimRepository;
import com.example.mobilkompaniya.Roles.Rolles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class XodimServis implements UserDetailsService {
    @Autowired
    RollRepository roleRepository;
    @Autowired
    XodimRepository xodimRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
        public ApiResponse creatDirector(DirectorDTO directorDTO){
            try{
                Xodimlar xodimlar=new Xodimlar();
                xodimlar.setFish(directorDTO.getFish());
                xodimlar.setTelRaqam(directorDTO.getTelRaqam());
                xodimlar.setUsername(directorDTO.getUsername());
                xodimlar.setPassword(passwordEncoder.encode(directorDTO.getPassword()));
                xodimlar.setRollar(roleRepository.findByRoleNomi(Rolles.DIRECTOR));
                xodimlar.setType(false);
                xodimlar.setEnabled(true);
                xodimRepository.save(xodimlar);
                return new ApiResponse("Direktor muavffaqiyatli qo'shildi.", true);
            }
            catch (Exception e){
                e.printStackTrace();
                return new ApiResponse("Ro'yxatdan o'tkazilgan", false);
            }
        }

        public ApiResponse creatManager(XodimDTO xodimDTO){
            Optional<Xodimlar> optionalXodimlar = xodimRepository.findById(xodimDTO.getXodimId());
            Optional<Rollar> optionalRollar = roleRepository.findById(xodimDTO.getXodimId());
            Optional<Rollar> rollarOptional = roleRepository.findById(xodimDTO.getRoleType());
            if(optionalXodimlar.isPresent()){
                if (optionalXodimlar.get().getRollar().getRoleNomi().equals(Rolles.DIRECTOR) || optionalXodimlar.get().getRollar().getRoleNomi().equals(Rolles.XODIM_MANAGER)){
                    Xodimlar xodimlar=new Xodimlar();
                    xodimlar.setFish(xodimDTO.getFish());
                    xodimlar.setUsername(xodimDTO.getUsername());
                    xodimlar.setTelRaqam(xodimDTO.getTelRaqam());
                    xodimlar.setPassword(passwordEncoder.encode(xodimDTO.getPassword()));
                    xodimlar.setRollar(rollarOptional.get());
                    if (!optionalRollar.get().getRoleNomi().equals(Rolles.DIRECTOR)){
                        return new ApiResponse(optionalRollar.get().getRoleNomi()+"Xodim qo'shishga ruxsat berilmagan"+optionalXodimlar.get().getRollar().getRoleNomi().toString(), false);
                    }
                   // xodimlar.setRollar(roleRepository.findByRoleNomi(optionalRollar.get().getRoleNomi()));
                    xodimlar.setType(false);
                    xodimRepository.save(xodimlar);
                    return new ApiResponse("Xodim ro'yxatga qo'shildi",true);
                }
                return new ApiResponse("Xodim qo'shib bo'lmaydi", false);
            }
            return new ApiResponse("Login va parol mavjud emas", false);
        }
        public ApiResponse hodimLogin(LoginDTO loginDTO){
            Optional<Xodimlar> byUsername = xodimRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
            if (byUsername.isPresent()){
                return new ApiResponse("Profilingizga hush kelibsiz", false);
            }
            return new ApiResponse("Bunday xodim mavjud emas", false);
        }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
