package com.blez.newsapi_springboot.service;

import com.blez.newsapi_springboot.model.*;
import com.blez.newsapi_springboot.repository.H2Repository;
import com.blez.newsapi_springboot.repository.PreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authManager;


    @Autowired
    private H2Repository h2Repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PreferenceRepository preferenceRepository;

    public String login(LoginModel loginModel) {
        UserTable user = h2Repository.findByUsername(loginModel.getUsername());
        if (user == null) {
            return "fail";
        }

        if (user.getIsVerified() == "false") {
            return verify(loginModel);
        }
        if (passwordEncoder.matches(loginModel.getPassword(), user.getPassword())) {
            return jwtService.generateToken(loginModel.getUsername());
        }


        return "fail";
    }

    public boolean register(RegisterModel registerModel) {
        if (h2Repository.findByUsername(registerModel.getUsername()) != null) {
            throw new RuntimeException("User already exists");

        }
        UserTable user = UserTable
                .builder()
                .username(registerModel.getUsername())
                .password(passwordEncoder.encode(registerModel.getPassword()))
                .email(registerModel.getEmail())
                .role("user")
                .isVerified("false")
                .build();
        UserTable save = h2Repository.save(user);
        if (save == null) {
            return false;
        }
        Preferences prefs = Preferences.builder()
                .topic("bitcon")
                .country("in")
                .category(Category.popularity)
                .language("en")
                .domain("techcrunch.com")
                .userTable(save)
                .build();

        preferenceRepository.save(prefs);


        return true;
    }

    public String verify(LoginModel user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            UserTable userTable = h2Repository.findByUsername(user.getUsername());
            userTable.setIsVerified("true");
            h2Repository.save(userTable);
            return jwtService.generateToken(user.getUsername());
        } else {
            return "fail";
        }
    }

}
