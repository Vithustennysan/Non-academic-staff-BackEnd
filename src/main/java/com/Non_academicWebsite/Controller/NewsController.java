package com.Non_academicWebsite.Controller;

import com.Non_academicWebsite.CustomException.UserNotFoundException;
import com.Non_academicWebsite.DTO.NewsDTO;
import com.Non_academicWebsite.Entity.News;
import com.Non_academicWebsite.Service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class NewsController {
    @Autowired
    private NewsService newsService;

    // all users have an access
    @GetMapping(value = "api/auth/news/get")
    public ResponseEntity<List<News>> getNews(@RequestHeader("Authorization") String header) throws UserNotFoundException {
        return ResponseEntity.ok(newsService.get(header));
    }

    // only admin can access
    @PostMapping(value = "api/admin/news/add")
    public ResponseEntity<List<News>> addNews(@RequestBody NewsDTO newsDTO,
                                              @RequestHeader("Authorization") String header) throws UserNotFoundException {
        return ResponseEntity.ok(newsService.add(newsDTO, header));
    }

    @PutMapping(value = "api/admin/news/update/{id}")
    public ResponseEntity<List<News>> updateNews(@PathVariable("id") Integer id,
                                                 @RequestHeader("Authorization") String header,
                                                 @RequestBody NewsDTO newsDTO) throws UserNotFoundException {
        return ResponseEntity.ok(newsService.update(id, newsDTO, header));
    }

    @DeleteMapping(value = "api/admin/news/delete/{id}")
    public ResponseEntity<List<News>> deleteNews(@PathVariable("id") Integer id,
                                                 @RequestHeader("Authorization") String header) throws UserNotFoundException {
        return ResponseEntity.ok(newsService.delete(id, header));
    }
}
