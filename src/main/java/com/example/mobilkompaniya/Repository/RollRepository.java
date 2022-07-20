package com.example.mobilkompaniya.Repository;

import com.example.mobilkompaniya.Model.Rollar;
import com.example.mobilkompaniya.Roles.Rolles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RollRepository extends JpaRepository<Rollar, Integer> {
    Rollar findByRoleNomi(Rolles rolles);
    Rollar findByRoleNomi(String roleNomi);
}
