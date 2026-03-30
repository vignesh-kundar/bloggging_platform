package com.vignesh.spring_blog.service;

import com.vignesh.spring_blog.repository.UserAuthProviderRepository;
import com.vignesh.spring_blog.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserAuthProviderRepository userAuthProviderRepository;

    public String generateJwtToken( String email ) {
        SecretKey key = Jwts.SIG.HS256.key().build();
        // need to update expiry time :)
        String jwt = Jwts.builder().subject(email).signWith(key).expiration(new Date(96000)).compact();
        return jwt;
    }

    public String extractEmail( String jwt ) {
        SecretKey key = Jwts.SIG.HS256.key().build();
        String email = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload().getSubject();
        return email;
    }

    public boolean isTokenValid() {
       // need to code here
        return false;
    }
}
