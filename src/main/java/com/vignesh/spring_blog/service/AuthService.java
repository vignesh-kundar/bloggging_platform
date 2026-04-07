package com.vignesh.spring_blog.service;

import com.vignesh.spring_blog.entity.UserAuthProvider;
import com.vignesh.spring_blog.entity.Users;
import com.vignesh.spring_blog.repository.UserAuthProviderRepository;
import com.vignesh.spring_blog.repository.UsersRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.naming.directory.InvalidAttributesException;
import java.util.NoSuchElementException;

@Service
public class AuthService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserAuthProviderRepository userAuthProviderRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(String name , String email ,String userName , String password ) {
        if (usersRepository.existsByEmail(email))
            throw new EntityExistsException("User with Email id : " + email + " already exists!");

        if (usersRepository.existsByUserName(userName))
            throw new EntityExistsException("User with UserName : " + userName + " already exists!");

        Users newUser = Users.builder()
                .name(name)
                .email(email)
                .userName(userName)
                .avatarUrl("https://api.dicebear.com/9.x/notionists/svg")
                .build();

        Users savedUser = usersRepository.save(newUser);

        UserAuthProvider userAuthProvider = UserAuthProvider.builder()
                .user(savedUser)
                .provider("local")
                .providerId(email)
                .providerHash(passwordEncoder.encode(password))
                .build();

        userAuthProviderRepository.save(userAuthProvider);

        return jwtService.generateJwtToken(email);

    }

    public String   loginUser(String email, String password) throws Exception {

        usersRepository.findByEmail(email).orElseThrow( () -> new NoSuchElementException("No User record with email Id : {} " + email + " found!"));

        UserAuthProvider userAuth = userAuthProviderRepository.
                findByProviderAndProviderId("local", email)
                .orElseThrow(() -> new InvalidAttributesException("No user with email found!"));

        // validate passowrd
        if (!passwordEncoder.matches(password , userAuth.getProviderHash())) {
            throw new InvalidAttributesException("Invalid Passoword");
        }

        return jwtService.generateJwtToken(email);
    }

}
