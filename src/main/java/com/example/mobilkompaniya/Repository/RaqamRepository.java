package com.example.mobilkompaniya.Repository;

import com.example.mobilkompaniya.Model.Kodi;
import com.example.mobilkompaniya.Model.Raqamlar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RaqamRepository extends JpaRepository<Raqamlar, Long> {
        Optional<Raqamlar> findByKodiAndTelraqam(Kodi kodi, String telraqam);
        Optional<Raqamlar> findByKodiAndTelraqam(String kodi, String telraqam);
        Optional<Raqamlar> findByKodi(Kodi kodi);
        //Optional<Raqamlar> findByKodiAndTelraqam(Integer kodi, String telraqam);

}
