package com.dd.studentcrud.entity;
import com.dd.studentcrud.dto.StudentDTO;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author DD
 */
@Data
@Entity
@Table(name = "student_mgmt")
public class Student {
    @Id// primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id", length = 45)
    private int studentId;
    @Column(name = "student_name", length = 80)
    private String studentName;
    @Column(name = "student_address", length = 150)
    private String address;
    @Column(name = "student_mobile", length = 45)
    private Long mobileNumber;
    @Column(name = "active_status", columnDefinition = "TINYINT default 1")
    private boolean active;

    //    default constructor
    public Student() {
    }

    public Student(int studentId, String studentName, String address, Long mobileNumber, boolean active) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.active = active;
    }

    public Student(String studentName, String address, Long mobileNumber, boolean active) {
        this.studentName = studentName;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.active = active;
    }
    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", address='" + address + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", active=" + active +
                '}';
    }

    public StudentDTO toDTO() {
        return StudentDTO.builder()
                .studentId(studentId)
                .studentName(studentName)
                .address(address)
                .mobileNumber(mobileNumber)
                .active(active)
                .build();
    }
}
