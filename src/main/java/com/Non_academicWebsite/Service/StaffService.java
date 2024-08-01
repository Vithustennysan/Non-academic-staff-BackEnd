package com.Non_academicWebsite.Service;

import com.Non_academicWebsite.Config.JwtService;
import com.Non_academicWebsite.DTO.RegisterDTO;
import com.Non_academicWebsite.DTO.SecurityDTO;
import com.Non_academicWebsite.Entity.Role;
import com.Non_academicWebsite.Entity.User;
import com.Non_academicWebsite.Repository.UserRepo;
import com.Non_academicWebsite.Response.UserInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class StaffService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public UserInfoResponse getUser(String token) {
        String email = jwtService.extractUserEmail(token);
        User user = userRepo.findByEmail(email).orElseThrow();

        String imageBase64 = Base64.getEncoder().encodeToString(user.getImage_data());
        return UserInfoResponse.builder()
                .first_name(user.getFirst_name())
                .last_name(user.getLast_name())
                .date_of_birth(user.getDate_of_birth())
                .gender(user.getGender())
                .email(user.getEmail())
                .phone_no(user.getPhone_no())
                .address(user.getAddress())
                .city(user.getCity())
                .postal_code(user.getPostal_code())
                .ic_no(user.getIc_no())
                .emp_id(user.getEmp_id())
                .job_type(user.getJob_type())
                .department(user.getDepartment())
                .faculty(user.getFaculty())
                .image_type(user.getImage_type())
                .image_name(user.getImage_name())
                .image_data(imageBase64)
                .isLogin(true)
                .build();
    }

    public void updateProfile(String header, RegisterDTO registerDTO) {
        String token = header.substring(7);
        String email = jwtService.extractUserEmail(token);

        User user = userRepo.findByEmail(registerDTO.getEmail()).orElseThrow();
        user.setFirst_name(registerDTO.getFirst_name());
        user.setLast_name(registerDTO.getLast_name());
        user.setDate_of_birth(registerDTO.getDate_of_birth());
        user.setGender(registerDTO.getGender());
        user.setEmail(registerDTO.getEmail());
        user.setPhone_no(registerDTO.getPhone_no());
        user.setAddress(registerDTO.getAddress());
        user.setCity(registerDTO.getCity());
        user.setPostal_code(registerDTO.getPostal_code());
        user.setIc_no(registerDTO.getIc_no());
        user.setEmp_id(registerDTO.getEmp_id());
        user.setJob_type(registerDTO.getJob_type());
        user.setDepartment(registerDTO.getDepartment());
        user.setFaculty(registerDTO.getFaculty());
        user.setUpdatedAt(new Date());

        userRepo.save(user);
    }

    public String resetPassword(String header, SecurityDTO resetPasswordDTO) {
        String token = header.substring(7);
        String email = jwtService.extractUserEmail(token);

        User user = userRepo.findByEmail(email).orElseThrow();
        String oldPassword = resetPasswordDTO.getOld_password();
        String newPassword = resetPasswordDTO.getNew_password();

        if(passwordEncoder.matches(oldPassword,user.getPassword())){
            if(!passwordEncoder.matches(newPassword, user.getPassword())){
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepo.save(user);
                return "reset success";
            }
            return "can't be same password";
        }
        return "reset rejected";
    }


    public String deleteAccount(String header, SecurityDTO deleteAccountDTO) {
        String token = header.substring(7);
        String email = jwtService.extractUserEmail(token);

        User user = userRepo.findByEmail(email).orElseThrow();
        String oldPassword = deleteAccountDTO.getPassword_for_delete();

        if(passwordEncoder.matches(oldPassword,user.getPassword())){
                userRepo.delete(user);
                return "delete success";
        }
        return "delete rejected";
    }
}
