package com.codewithproject.springsecurity.controller;

import com.codewithproject.springsecurity.dto.JwtAuthenticationResponse;
import com.codewithproject.springsecurity.dto.RefreshTokenRequest;
import com.codewithproject.springsecurity.dto.SignInRequest;
import com.codewithproject.springsecurity.dto.SignUpRequest;
import com.codewithproject.springsecurity.entities.User;
import com.codewithproject.springsecurity.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/public")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest) {
        System.out.println(signUpRequest.getEmail());
        System.out.println(signUpRequest.getPassword());
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signin(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

    @GetMapping("/login/google")
    public String googleLogin() {
        return "login"; // Return the login view name
        // Tao duong dan <a href="/oauth2/authorization/google">Login with Google</a>
    }

//    @GetMapping("/login/google/callback")
//    public String googleCallback(Authentication authentication) {
//        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
//        // Process the user details retrieved from Google
//        // You can access user attributes using oauth2User.getAttributes()
//        return "redirect:/home";
//    }
}

//Test the Google login:
//Start your Spring Boot application.
//Access the login page (e.g., http://localhost:8080/login) and click on the "Login with Google" link.
//You should be redirected to the Google login page.
//After successful authentication, Google will redirect the user back to your application's redirect URI.
//Handle the user information and perform any necessary operations (e.g., user registration) in the appropriate endpoint.