package com.crud.practice.crud.controller;

import com.crud.practice.crud.entity.Student;
import com.crud.practice.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService service;

    @GetMapping("/student")
    public List<Student> getAllStudentList(){
        return service.getAllUserList();
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable int id){
        return service.getUserById(id);
    }

    @PostMapping("/student")
    public Student addData(@RequestBody Student newStudent){
        return service.addStudent(newStudent);
    }

    @PutMapping("/student/{id}")
    public Student updateUser(@PathVariable int id , @RequestBody Student student){
        return service.updateStudent(id , student);
    }

    @DeleteMapping("/student/{id}")
    public String deleteUser(@PathVariable int id){
        return service.deleteStudentFromList(id);
    }
}
