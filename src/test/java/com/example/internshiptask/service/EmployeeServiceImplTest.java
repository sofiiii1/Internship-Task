package com.example.internshiptask.service;

import com.example.internshiptask.dao.EmployeeDao;
import com.example.internshiptask.dto.Employee;
import com.example.internshiptask.dto.Gender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee1;
    private Employee employee2;
    List<Employee> employeeList;

    @BeforeEach
    public void setUp(){
        employeeList=new ArrayList<>();
        employee1=new Employee(100, "Lana", "Doe", "Marketing", "Marketing analyst", Gender.FEMALE, new Date(1999,8,25));
        employee2=new Employee(99, "Mick", "Li", "IT", "Programmer", Gender.MALE, new Date(1993,2,10));
        employeeList.add(employee1);
        employeeList.add(employee2);
    }

    @AfterEach
    public void tearDown(){
        employee1=null;
        employee2=null;
        employeeList=null;
    }

    @Test
    void getById_success() {
        when(employeeDao.getById(100)).thenReturn(employee1);
        assertThat(employeeService.getById(employee1.getEmployeeId())).isEqualTo(employee1);
    }

    @Test
    void getAll_success() {
        employeeDao.saveEmployee(employee1);
        when(employeeDao.getAllEmployee()).thenReturn(employeeList);
        List<Employee> expected=employeeService.getAllEmployee();
        assertEquals(expected,employeeList);
        verify(employeeDao, times(1)).saveEmployee(employee1);
        verify(employeeDao,times(1)).getAllEmployee();
    }

    @Test
    void saveEmployee_success() {
        when(employeeDao.saveEmployee(any())).thenReturn(employee1);
        employeeService.saveEmployee(employee1);
        verify(employeeDao,times(1)).saveEmployee(any());
    }

    @Test
    void updateEmployee_success() {
        when(employeeDao.updateEmployee(employee1)).thenReturn(employee1);
        employee1.setFirstName("Nick");
        Employee newEmployee=employeeService.updateEmployee(employee1);
        assertThat(newEmployee.getFirstName()).isEqualTo("Nick");
    }

    @Test
    void deleteEmployee_success() {
        employeeDao.deleteEmployee(100);
        verify(employeeDao).deleteEmployee(employee1.getEmployeeId());
    }
}