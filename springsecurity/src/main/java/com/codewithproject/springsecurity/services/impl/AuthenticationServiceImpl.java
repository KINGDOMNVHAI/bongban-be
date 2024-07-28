package com.codewithproject.springsecurity.services.impl;

import com.codewithproject.springsecurity.config.MessageConstants;
import com.codewithproject.springsecurity.dto.request.RefreshTokenRequest;
import com.codewithproject.springsecurity.dto.request.SignInRequest;
import com.codewithproject.springsecurity.dto.request.SignUpRequest;
import com.codewithproject.springsecurity.dto.response.JwtAuthenticationResponse;
import com.codewithproject.springsecurity.entities.User;
import com.codewithproject.springsecurity.enums.Role;
import com.codewithproject.springsecurity.repository.UserRepository;
import com.codewithproject.springsecurity.services.AuthenticationService;
import com.codewithproject.springsecurity.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    public User signup(SignUpRequest signUpRequest) {
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstname(signUpRequest.getFirstname());
        user.setLastname(signUpRequest.getSecondname());
        user.setUsername(signUpRequest.getSecondname());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        return userRepository.save(user);
    }

    public JwtAuthenticationResponse signin(SignInRequest signInRequest) {
        String email = "";
        String username = "";
        String pass = signInRequest.getPassword();
        String passHash = new BCryptPasswordEncoder().encode(pass);
        String jwt = null;
        String refreshToken = null;
        String message = MessageConstants.MESS_INVALID_EMAIL_PASSWORD;

        Optional<User> user = userRepository.getUserByEmailOrUsername(signInRequest.getEmailOrUsername());
        if (user.isPresent() && !pass.isEmpty()) {
            User dto = user.get();
            if (dto.getPassword().equals(pass)) {
                jwt = jwtService.generateToken(dto);
                refreshToken = jwtService.generateRefreshToken(new HashMap<>(), dto);
                message = MessageConstants.MESS_LOGIN_SUCCESS;
                email = dto.getEmail();
                username = dto.getUsername();
            }
        }
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setEmail(email);
        jwtAuthenticationResponse.setUsername(username);
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        jwtAuthenticationResponse.setMessage(message);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    signInRequest.getEmailOrUsername(), passHash));
        } catch (Exception ex) {
            System.out.println("Error");
            System.out.println(ex);
        }
        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }

    public JwtAuthenticationResponse loginGoogle() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2User auth2User = (OAuth2User) authentication.getPrincipal();
        return getInfoLoginGoogle(auth2User);
    }

    public JwtAuthenticationResponse getInfoLoginGoogle(OAuth2User principal) {
        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        if (principal != null) {
            String picture = principal.getAttribute("picture");
            response.setEmail(principal.getAttribute("email"));
            response.setFirstname(principal.getAttribute("given_name"));
            response.setLastname(principal.getAttribute("family_name"));
            response.setLocale(principal.getAttribute("locale"));
        }
        return response;
    }

    private void insertLoginGoogleToDB() {
        System.out.println("insertLoginGoogleToDB");
    }
}
