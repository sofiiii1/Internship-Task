package com.example.internshiptask.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("departments")
public class Department {
    @Id
    private Long departmentId;
    private String departmentName;

    @Override
    public String toString() {
        return departmentName;
    }
}
