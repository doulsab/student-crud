package com.dd.studentcrud.service;
/**
 * @author DD
 */

import com.dd.studentcrud.dto.StudentDTO;
import com.dd.studentcrud.entity.Student;
import com.dd.studentcrud.exception.DataNotFoundException;

import java.util.List;

public interface StudentService {
    Student addStudent(StudentDTO studentDTO);

    List<Student> getStudents();

    Student updateStudent(StudentDTO studentDTO) throws DataNotFoundException;

    boolean deleteById(int studentId);

    Student partiallyUpdateStudent(int studentId, StudentDTO studentDTO) throws DataNotFoundException;
}
