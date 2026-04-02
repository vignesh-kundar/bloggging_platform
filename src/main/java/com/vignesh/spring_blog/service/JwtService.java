package com.vignesh.spring_blog.service;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Service
public class JwtService {

    @Value("${app.jwt.secret}")
    protected String jwtSecretKey;

    @Value("${app.jwt.expiration}")
    protected long jwtExpiration;

    public SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateJwtToken( String email ) {
        return Jwts.builder().
                subject(email).
                issuedAt(new Date(System.currentTimeMillis())).
                expiration(new Date(System.currentTimeMillis() + jwtExpiration)).
                signWith(getSigninKey()).
                compact();
    }

    public String extractEmail( String jwt ) {
        return Jwts.parser().
                verifyWith(getSigninKey()).
                build().
                parseSignedClaims(jwt).
                getPayload().
                getSubject();
    }

    public boolean isTokenValid( String jwt ) {
       try {
           String subject = extractEmail(jwt);
           log.info("Extracted jwt token for : {}" , subject);
           return true;
       } catch (Exception ex) {
           log.error("Failed to extract Jwt Token : {}" , ex.getMessage());
           return false;
       }
    }
}
