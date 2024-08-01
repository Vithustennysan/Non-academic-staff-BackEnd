package com.Non_academicWebsite.Service.Forms;

import com.Non_academicWebsite.DTO.Forms.FullLeaveFormDTO;
import com.Non_academicWebsite.Entity.Forms.FullLeaveForm;
import com.Non_academicWebsite.Repository.Forms.FullLeaveFormRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FullLeaveFormService {
    @Autowired
    private FullLeaveFormRepo fullLeaveFormRepo;

    public String submitForm(FullLeaveFormDTO fullLeaveFormDTO, MultipartFile file) throws IOException {

        FullLeaveForm form = FullLeaveForm.builder()
                .name(fullLeaveFormDTO.getName())
                .emp_id(fullLeaveFormDTO.getEmp_id())
                .faculty(fullLeaveFormDTO.getFaculty())
                .department(fullLeaveFormDTO.getDepartment())
                .job_start_date(fullLeaveFormDTO.getJob_start_date())
                .leave_days(fullLeaveFormDTO.getLeave_days())
                .leave_type(fullLeaveFormDTO.getLeave_type())
                .start_date(fullLeaveFormDTO.getStart_date())
                .end_date(fullLeaveFormDTO.getEnd_date())
                .acting(fullLeaveFormDTO.getActing())
                .reason(fullLeaveFormDTO.getReason())
                .file_data(file.getBytes())
                .file_name(file.getOriginalFilename())
                .file_type(file.getContentType())
                .build();

        fullLeaveFormRepo.save(form);
        return "Submitted Successfully";
    }
}