package com.Non_academicWebsite.Controller.ApprovalFlow;

import com.Non_academicWebsite.DTO.ApprovalDTO;
import com.Non_academicWebsite.Service.ApprovalFlow.FormApproverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/admin/formApprover")
public class FormApproverController {

    @Autowired
    private FormApproverService formApproverService;

    @GetMapping(value = "/get/{formId}")
    public ResponseEntity<?> getApprover(@RequestHeader("Authorization") String header,
                                         @PathVariable("formId") Long formId){
        return ResponseEntity.ok(formApproverService.getApprover(formId, header));
    }

    @PostMapping(value = "accept/{approverId}")
    public ResponseEntity<?> acceptForm(@RequestHeader("Authorization") String header,
                                         @PathVariable("approverId") Long approverId,
                                         @RequestBody ApprovalDTO approvalDTO){
        return ResponseEntity.ok(formApproverService.acceptForm(approverId, approvalDTO, header));
    }

    @PostMapping(value = "reject/{approverId}")
    public ResponseEntity<?> rejectForm(@RequestHeader("Authorization") String header,
                                         @PathVariable("approverId") Long approverId,
                                         @RequestBody ApprovalDTO approvalDTO){
        return ResponseEntity.ok(formApproverService.rejectForm(approverId, approvalDTO, header));
    }
}
