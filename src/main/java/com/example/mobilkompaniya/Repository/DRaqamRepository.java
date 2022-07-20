package com.example.mobilkompaniya.Repository;

import com.example.mobilkompaniya.Model.DavlatKodi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.jaas.JaasPasswordCallbackHandler;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DRaqamRepository extends JpaRepository<DavlatKodi, Integer>{

}
