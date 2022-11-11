package com.example.internshiptask.dao;

import com.example.internshiptask.dto.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDao {
    List<Employee> getAllEmployee();
    Employee getById(int id);
    void deleteEmployee(int id);
    Employee saveEmployee(Employee employee);
    Employee updateEmployee(Employee employee);

}
