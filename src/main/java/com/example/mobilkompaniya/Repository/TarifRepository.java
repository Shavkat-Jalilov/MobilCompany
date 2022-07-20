package com.example.mobilkompaniya.Repository;

import com.example.mobilkompaniya.Model.Tariflar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TarifRepository extends JpaRepository<Tariflar, Integer> {
    Optional<Tariflar> findByNomi(String nomi);
  //  Optional<Tariflar> findByNomi(String nomi);

}
