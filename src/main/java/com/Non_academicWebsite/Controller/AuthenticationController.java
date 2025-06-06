package com.Non_academicWebsite.Controller;

import com.Non_academicWebsite.CustomException.UnauthorizedAccessException;
import com.Non_academicWebsite.CustomException.UserNotFoundException;
import com.Non_academicWebsite.DTO.LoginDTO;
import com.Non_academicWebsite.DTO.RegisterDTO;
import com.Non_academicWebsite.Response.AuthenticationResponse;
import com.Non_academicWebsite.Service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("api/auth")
@CrossOrigin
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping(value = "/signup")
    public ResponseEntity<?> register(@Valid @ModelAttribute RegisterDTO registerDTO,
                                      @RequestParam(value = "image", required = false) MultipartFile image) throws Exception {
        System.out.println(registerDTO.toString());
        return ResponseEntity.ok(authenticationService.registerStaff(registerDTO, image));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginDTO loginDTO) throws UserNotFoundException,
                                                                                        UnauthorizedAccessException {
        return ResponseEntity
                .ok(authenticationService.login(loginDTO));
    }

}
