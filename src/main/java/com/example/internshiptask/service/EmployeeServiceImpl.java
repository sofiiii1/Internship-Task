package com.example.internshiptask.service;

import com.example.internshiptask.dao.EmployeeDao;
import com.example.internshiptask.dao.EmployeeDaoImpl;
import com.example.internshiptask.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao){
        this.employeeDao=employeeDao;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeDao.getAllEmployee();
    }

    @Override
    public Employee getById(int id) {
        return employeeDao.getById(id);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeDao.deleteEmployee(id);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeDao.saveEmployee(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }
}
