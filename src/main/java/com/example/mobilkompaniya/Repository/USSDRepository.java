package com.example.mobilkompaniya.Repository;

import com.example.mobilkompaniya.Model.USSD;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface USSDRepository extends JpaRepository<USSD, Integer> {
    Optional<USSD> findByUssdNomiAndUssdRaqam(String ussdNomi, String ussdRaqam);
   // Optional<USSD> findByUssdNomiAndUssdRaqam(String ussdNomi, String ussdRaqam);
}
