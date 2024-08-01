package com.Non_academicWebsite.Controller.Forms;

import com.Non_academicWebsite.DTO.Forms.FullLeaveFormDTO;
import com.Non_academicWebsite.Service.Forms.FullLeaveFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/auth/full_leave_form")
public class FullLeaveFormController {
    @Autowired
    private FullLeaveFormService fullLeaveFormService;

    @PostMapping(value = "/send")
    public ResponseEntity<?> submitForm(@ModelAttribute FullLeaveFormDTO fullLeaveFormDTO,
                                        @RequestParam(value = "files", required = false) MultipartFile file ) throws IOException {

        return ResponseEntity.ok(fullLeaveFormService.submitForm(fullLeaveFormDTO,file));
    }
}