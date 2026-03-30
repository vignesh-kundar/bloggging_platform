package com.vignesh.spring_blog.service;

import com.vignesh.spring_blog.repository.UserAuthProviderRepository;
import com.vignesh.spring_blog.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Service
public class JwtService {

    @Value("${app.jwt.secret}")
    protected String JwtSecretKey;

    @Value("${app.jwt.expiration}")
    protected String JwtExpiration;

    public String generateJwtToken( String email ) {
        SecretKey key = Jwts.SIG.HS256.key().build();
        // need to update expiry time :)
        String jwt = Jwts.builder().subject(email).signWith(key).expiration(new Date(System.currentTimeMillis() + JwtExpiration)).compact();
        return jwt;
    }

    public String extractEmail( String jwt ) throws Exception {
        SecretKey key = Jwts.SIG.HS256.key().build();
        String email = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload().getSubject();
        return email;
    }

    public boolean isTokenValid( String jwt ) {
       try {
           String subject = extractEmail(jwt);
           log.info("Extracted jwt token for : {}" , subject);
           return true;
       } catch (Exception ex) {
           log.error("Failed to extract Jwt Token");
           return false;
       }
    }
}
