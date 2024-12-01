package com.Non_academicWebsite.Repository.ApprovalFlow;

import com.Non_academicWebsite.Entity.ApprovalFlow.FormApprover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface FormApproverRepo extends JpaRepository<FormApprover, Long> {
    List<FormApprover> findByFormId(Long id);
}