package com.dd.studentcrud.dto;
/**
 * @author DD
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO {
    private int studentId;
    private String studentName;
    private String address;
    private Long mobileNumber;
    private boolean active;
}
