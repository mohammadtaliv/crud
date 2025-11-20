package com.crud.practice.crud.controller;

import com.crud.practice.crud.entity.User;
import com.crud.practice.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService service;

    @GetMapping("/allstudentlist")
    public List<User> getAllStudentList(){
        return service.getAllUserList();
    }

    @GetMapping("/student/{id}")
    public User getStudentById(@PathVariable int id){
        int userid = id;
        return service.getUserById(userid);
    }

    @PostMapping("/addstudent")
    public User addData(@RequestBody User newUser){
        return service.addStudent(newUser);
    }

    @PutMapping("/updateuser/{id}")
    public User updateUser(@PathVariable int id , @RequestBody User user){
        return service.updateStudentList(id , user);
    }

    @DeleteMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable int id){
        return service.deleteStudentFromList(id);
    }
}
