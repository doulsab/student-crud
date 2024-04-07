package com.dd.studentcrud.controller;
/**
 * @author DD
 */

import com.dd.studentcrud.dto.StudentDTO;
import com.dd.studentcrud.entity.Student;
import com.dd.studentcrud.exception.DataNotFoundException;
import com.dd.studentcrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    //  1) Save Student
    @PostMapping("/save")
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO) {
        Student savedStudent = studentService.addStudent(studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent.toDTO());
    }

    // 2) Get All Students
    @GetMapping("/")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    //3) Update the Student details Full DTO

    @PutMapping("/update")
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO) throws DataNotFoundException {
        Student updateStudent = studentService.updateStudent(studentDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updateStudent.toDTO());
    }

    //4) Update the DTO partially
    @PatchMapping("/update/{studentId}")
    public ResponseEntity<StudentDTO> updateStudentPartially(
            @PathVariable int studentId,
            @RequestBody StudentDTO studentDTO) throws DataNotFoundException {
        Student updatedStudent = studentService.partiallyUpdateStudent(studentId, studentDTO);
        return ResponseEntity.ok(updatedStudent.toDTO());
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<?> deleteStudentById(@PathVariable int studentId) {
        boolean isDeleted = studentService.deleteById(studentId);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
