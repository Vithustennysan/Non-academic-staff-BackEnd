package com.Non_academicWebsite.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String heading;
    @Lob
    @Column(length = 512000)
    private String body;
    private Date createdAt;
    private Date updatedAt;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private String filename;
    private String filetype;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] fileData;

}
