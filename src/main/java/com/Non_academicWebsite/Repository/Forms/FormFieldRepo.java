package com.Non_academicWebsite.Repository.Forms;

import com.Non_academicWebsite.Entity.Forms.FormField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface FormFieldRepo extends JpaRepository<FormField,Long> {
    Long findByDynamicFormIdAndSequence(Long id, int fieldOrder);
    List<FormField> findAllByDynamicFormId(Long id);
}
