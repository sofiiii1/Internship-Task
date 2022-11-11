package com.example.internshiptask.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@NoArgsConstructor
@Table("employee")
public class Employee {
    @Id
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String department;
    private String jobTitle;
    private Gender gender;
    private Date dateOfBirth;

}


