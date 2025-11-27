package com.crud.practice.crud.service;

import com.crud.practice.crud.entity.Student;
import com.crud.practice.crud.exception.BadRequestException;
import com.crud.practice.crud.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private static final Logger logger = LogManager.getLogger(StudentService.class);

    public Student addStudent(Student student){
        logger.info("Adding student {}" , student );
        if (student.getName() == null || student.getName().isBlank()){
            logger.error("Student name can not be empty");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
            Student savedStudent = studentRepository.save(student);
            logger.info("Student added successfully with ID: {}", savedStudent.getId());
            return savedStudent;
    }

    public List<Student> getAllUserList(){
        logger.info("fetching all the student from Student Repository");
        return studentRepository.findAll();
    }

    public Student getUserById(int id){
        logger.info("Fetching student with ID: {}", id);
        return studentRepository.findById(id)
        .orElseThrow(() ->{
            logger.error("Student not found with ID: {}" , id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }

    public Student updateStudent(int id , Student updateStudent){
        logger.info("Updating student with ID: {}", id);
        return studentRepository.findById(id).map(student -> {
            student.setName(updateStudent.getName());
            student.setAge(updateStudent.getAge());
            Student updatedSavedStudent= studentRepository.save(student);
            logger.info("Student Details updated with id {}: {}", id, updatedSavedStudent);
            return updatedSavedStudent;
        }).orElseThrow(() ->{
            logger.error("Failed to update. Student not found with ID: {}", id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });

    }

    public String deleteStudentFromList(int id){
        logger.info("Deleting student with ID: {}", id);
        if (studentRepository.existsById(id)){
            studentRepository.deleteById(id);
            logger.info("Student deleted successfully with ID: {}", id);
            return "Student delete successfully";
        }
        logger.error("Delete failed. Student not found with ID: {}", id);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
