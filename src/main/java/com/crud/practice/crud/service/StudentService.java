package com.crud.practice.crud.service;

import com.crud.practice.crud.entity.Student;
import com.crud.practice.crud.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;


    public Student addStudent(Student student){
        studentRepository.save(student);
        System.out.println(studentRepository.toString() + "User added successfully");
        return student;
    }

    public List<Student> getAllUserList(){
        return studentRepository.findAll();
    }

    public Student getUserById(int id){
        return studentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Student not fount with id " + id));
    }

    public Student updateStudent(int id , Student updateStudent){
        return studentRepository.findById(id).map(student -> {
            student.setName(updateStudent.getName());
            student.setAge(updateStudent.getAge());
            return studentRepository.save(student);
        }).orElseThrow(() -> new RuntimeException("Student not found with id" + id));
    }

    public String deleteStudentFromList(int id){
        if (studentRepository.existsById(id)){
            studentRepository.deleteById(id);
            return "Student delete successfully";
        }
        throw new RuntimeException("Student not found with id " + id);
    }

}
