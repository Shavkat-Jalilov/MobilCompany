package com.example.mobilkompaniya.Repository;

import com.example.mobilkompaniya.Model.Kodi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KodRepository extends JpaRepository<Kodi, Integer> {
  Optional<Kodi> findByCode(String code);
}
