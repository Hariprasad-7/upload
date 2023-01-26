package com.file.uploadDownload.service;


import com.file.uploadDownload.entity.Course;
import com.file.uploadDownload.repository.CourseRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CourseService {


    @Autowired
    private CourseRepository courseRepository;


    public Course saveCourse(Course course){
        return courseRepository.save(course);

    }

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public void generateExcel(HttpServletResponse response) throws IOException {

        List<Course> courseList =  courseRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Couse details");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("Name");
        row.createCell(2).setCellValue("Price");

        int dataRowIndex = 1;
        for(Course course : courseList){
            HSSFRow rows = sheet.createRow(dataRowIndex);
            rows.createCell(0).setCellValue(course.getId());
            rows.createCell(1).setCellValue(course.getName());
            rows.createCell(2).setCellValue(course.getPrice());
            dataRowIndex ++;

        }

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

    }


}
