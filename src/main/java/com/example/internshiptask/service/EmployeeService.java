package com.example.internshiptask.service;

import com.example.internshiptask.dto.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployee();
    Employee getById(int id);
    void deleteEmployee(int id);
    Employee saveEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
}
