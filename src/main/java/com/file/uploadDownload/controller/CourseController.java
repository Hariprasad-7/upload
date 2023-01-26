package com.file.uploadDownload.controller;


import com.file.uploadDownload.entity.Course;
import com.file.uploadDownload.service.CourseService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;


    @PostMapping("/save")
    public Course saveCourse(@RequestBody Course course){
        return courseService.saveCourse(course);

    }

    @GetMapping("/get")
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/excel")
    public void generateExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue ="attachment;filename=hari.xls";
        response.setHeader(headerKey,headerValue);
        courseService.generateExcel(response);

    }
}
