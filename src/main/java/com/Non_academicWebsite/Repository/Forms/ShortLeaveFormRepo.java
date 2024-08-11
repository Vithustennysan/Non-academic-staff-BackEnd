package com.Non_academicWebsite.Repository.Forms;

import com.Non_academicWebsite.Entity.Forms.ShortLeaveForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface ShortLeaveFormRepo extends JpaRepository<ShortLeaveForm, Long> {
    List<ShortLeaveForm> findByUserIdStartingWith(String prefix);
    List<ShortLeaveForm> findByFacultyAndDepartment(String faculty, String department);
    List<ShortLeaveForm> findByFaculty(String faculty);
}
