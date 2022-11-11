package com.example.internshiptask.dao;

import com.example.internshiptask.dto.Department;


public interface DepartmentDao {
    String getNameById(Long id);
    Long getIdByName(String department);

}
