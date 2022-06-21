package com.zzg.controller;

import com.zzg.mapper.StudentMapper;
import com.zzg.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("/save")
    public String insert() {
        Student student = new Student();
        student.setName("zzg" + new Random().nextInt());
        student.setSex(new Random().nextInt(2) + 1);
        student.setAge(new Random().nextInt(6) + 6);
        studentMapper.addStudent(student);
        return "success";
    }
}
