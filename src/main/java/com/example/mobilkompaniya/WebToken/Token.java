package com.example.mobilkompaniya.WebToken;

import com.example.mobilkompaniya.Roles.Rolles;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Token {
    long time=360_000_000;
    Date muddati=new Date(System.currentTimeMillis()+time);
    String parol="shavkat";
    public String getToken(String username, Rolles rols){
        String token= Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(muddati)
                .claim("ROLS",rols)
                .signWith(SignatureAlgorithm.HS512, parol)
                .compact();
        return token;
    }
    public boolean tokenCheck(String token){
        Jwts
                .parser()
                .setSigningKey(parol)
                .parseClaimsJws(token);
        return true;
    }
    public String userCheck(String username){
        String s = Jwts
                .parser()
                .setSigningKey(parol)
                .parseClaimsJws(username)
                .getBody()
                .getSubject();
        return s;
    }
}
