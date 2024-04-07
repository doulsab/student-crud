package com.dd.studentcrud.service.impl;
/**
 * @author DD
 */

import com.dd.studentcrud.dto.StudentDTO;
import com.dd.studentcrud.entity.Student;
import com.dd.studentcrud.exception.DataNotFoundException;
import com.dd.studentcrud.repo.StudentRepo;
import com.dd.studentcrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepo studentRepo;

    @Override
    public Student addStudent(StudentDTO studentDTO) {
        Student studentObject = new Student(studentDTO.getStudentName(), studentDTO.getAddress(), studentDTO.getMobileNumber(), studentDTO.isActive());
        System.out.println("Saved Object " + studentObject);
        return studentRepo.saveAndFlush(studentObject);
    }

    @Override
    public List<Student> getStudents() {

        List<Student> students = studentRepo.findAll();
        students.stream().map(this::mapStudentToDTO).collect(Collectors.toList());
        return students;
    }

    @Override
    public Student updateStudent(StudentDTO studentDTO) throws DataNotFoundException {
        int studentId = studentDTO.getStudentId();//get the student id
        Optional<Student> studentOptional = studentRepo.findById(studentId);//get the details from database by pass the id
        Student existingStudent = studentOptional.orElseThrow(() -> new DataNotFoundException("Student data not found " + studentId));//throw the exception if existingStudent is not present by passed id

        existingStudent.setStudentName(studentDTO.getStudentName());
        existingStudent.setAddress(studentDTO.getAddress());
        existingStudent.setMobileNumber(studentDTO.getMobileNumber());
        System.out.println("update Successfully! " + studentId);
        return studentRepo.saveAndFlush(existingStudent);
    }

    @Override
    public boolean deleteById(int studentId) {
        Optional<Student> existedStudent = studentRepo.findById(studentId);
        if (existedStudent.isPresent()) {
            studentRepo.deleteById(studentId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Student partiallyUpdateStudent(int studentId, StudentDTO studentDTO) throws DataNotFoundException {
        Optional<Student> studentOptional = studentRepo.findById(studentId);
        Student existingStudent = studentOptional.orElseThrow(() -> new DataNotFoundException("Student not found with ID: " + studentId));

        if (studentDTO.getStudentName() != null) {
            existingStudent.setStudentName(studentDTO.getStudentName());
        }
        if (studentDTO.getAddress() != null) {
            existingStudent.setAddress(studentDTO.getAddress());
        }
        if (studentDTO.getMobileNumber() != null) {
            existingStudent.setMobileNumber(studentDTO.getMobileNumber());
        }
        existingStudent.setActive(studentDTO.isActive());

        return studentRepo.saveAndFlush(existingStudent);
    }

    private StudentDTO mapStudentToDTO(Student student) {
        return StudentDTO.builder().studentId(student.getStudentId()).studentName(student.getStudentName()).address(student.getAddress()).mobileNumber(student.getMobileNumber()).active(student.isActive()).build();
    }
}
