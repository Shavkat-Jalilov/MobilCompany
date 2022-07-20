package com.example.mobilkompaniya.Model;

import com.example.mobilkompaniya.Roles.Rolles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Rollar implements GrantedAuthority {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
  @Enumerated(EnumType.STRING)
   private Rolles roleNomi;
    @Override
    public String getAuthority() {
        return roleNomi.toString();
    }
}
