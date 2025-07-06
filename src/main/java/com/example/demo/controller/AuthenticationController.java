package com.example.demo.controller;


import com.example.demo.dto.*;
import com.example.demo.model.User;
import com.example.demo.responses.LoginResponse;
import com.example.demo.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    private final StudentService service;

    private final ConsentFormService consentService;

    private final PersonalDetailsService personalDetailsService;
    private final PaperAnswerService paperAnswerService;


    public AuthenticationController(
            JwtService jwtService,
            AuthenticationService authenticationService,
            StudentService service,
            PersonalDetailsService personalDetailsService1,
            ConsentFormService consentService,
            PaperAnswerService paperAnswerService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.personalDetailsService = personalDetailsService1;
        this.service = service;
        this.consentService = consentService;
        this.paperAnswerService = paperAnswerService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto){
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestBody VerifyUserDto verifyUserDto) {
        try {
            authenticationService.verifyUser(verifyUserDto);
            return ResponseEntity.ok("Account verified successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/resend")
    public ResponseEntity<?> resendVerificationCode(@RequestParam String email) {
        try {
            authenticationService.resendVerificationCode(email);
            return ResponseEntity.ok("Verification code sent");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/batch")
    public ResponseEntity<String> submitAnswers(@RequestBody BatchAnswerRequest request) {
        log.info("Request arrived");
        paperAnswerService.saveBatchAnswers(request);
        return ResponseEntity.ok("Answers submitted successfully");
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest request) {
        log.info("create request hit");
        service.createUser(request.getUserId(),request.getUsername());
        return ResponseEntity.ok("done");
    }
    @PostMapping("/saveConsent")
    public ResponseEntity<ConsentAllResponse> saveColumns(@RequestBody ConsentColumnsRequest request) {
        ConsentAllResponse response = consentService.saveColumnsConsent(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/personal")
    public ResponseEntity<PersonalDetailsResponse> savePersonal(
            @RequestBody PersonalDetailsRequest request
    ) {
        PersonalDetailsResponse response = personalDetailsService.saveColumnsConsent(request);
        return ResponseEntity.ok(response);
    }

}