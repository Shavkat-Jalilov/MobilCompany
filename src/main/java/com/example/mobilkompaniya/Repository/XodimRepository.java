package com.example.mobilkompaniya.Repository;

import com.example.mobilkompaniya.Model.Filiallar;
import com.example.mobilkompaniya.Model.Xodimlar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface XodimRepository extends JpaRepository<Xodimlar, Integer> {
    Optional<Xodimlar> findByUsernameAndPassword(String username, String password);
   // Optional<Xodimlar> findByUsernameAndPassword(String username, String password);
    Optional<Xodimlar> findByFiliallar(Filiallar filiallar);
    Optional<Xodimlar> findByUsername(String username);
    //Optional<Xodimlar> findByFiliallar(Filiallar filiallar);
}
