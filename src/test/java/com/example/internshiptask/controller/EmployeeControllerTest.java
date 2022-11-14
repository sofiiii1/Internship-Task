package com.example.internshiptask.controller;

import com.example.internshiptask.dto.Employee;
import com.example.internshiptask.dto.Gender;
import com.example.internshiptask.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.CoreMatchers.is;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class EmployeeControllerTest {
    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    private List<Employee> employeeList;
    private Employee employee1;
    private Employee employee2;

    ObjectMapper objectMapper=new ObjectMapper();

    @BeforeEach
    public void setUp(){
        this.employeeList=new ArrayList<>();
        this.employee1= new Employee(100, "Lana", "Doe", "Marketing", "Marketing analyst", Gender.FEMALE, new Date(1999,8,25));
        this.employee2=new Employee(99, "Mick", "Li", "IT", "Programmer", Gender.MALE, new Date(1993,2,10));
        employeeList.add(employee1);
        employeeList.add(employee2);
    }

    @Test
    void getAll_success() throws Exception {
        when(employeeService.getAllEmployee()).thenReturn(employeeList);
        mockMvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(("$.size()"),is(employeeList.size())));
    }

    @Test
    void getById_success() throws Exception {
        when(employeeService.getById(99)).thenReturn(employee2);
        mockMvc.perform(get("/employee/99"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(("$.firstName"), is(employee2.getFirstName())))
                .andExpect(jsonPath("$.lastName",is(employee2.getLastName())));
    }

    @Test
    void saveEmployee_success() throws Exception {
        when(employeeService.saveEmployee(any())).thenReturn(employee1);
        employeeService.saveEmployee(employee1);
        mockMvc.perform(post("/employee").content(objectMapper.writeValueAsString(employee1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value(employee1.getFirstName()));
    }

    @Test
    void deleteEmployee_success() throws Exception{
        mockMvc.perform(delete("/employee/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateEmployee_success() throws Exception{
        when(employeeService.updateEmployee(employee2)).thenReturn(employee2);
        employee2.setFirstName("Nick");
        Employee employee=employeeService.updateEmployee(employee2);
        mockMvc.perform(put("/employee")
                        .content(objectMapper.writeValueAsString(employee2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName",is(employee2.getFirstName())));

    }
}