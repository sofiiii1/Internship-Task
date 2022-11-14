package com.example.internshiptask.dao;

import com.example.internshiptask.dto.Department;
import com.example.internshiptask.dto.Employee;
import com.example.internshiptask.dto.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DepartmentDao departmentDao;

    private final static String SELECT_ALL_EMPLOYEE_SQL="SELECT e.employee_id, e.first_name, e.last_name,e.department_id, e.job_title, e.date_of_birth, e.gender\n" +
            "FROM employee as e \n" +
            "inner join departments as d \n" +
            "on e.department_id=d.department_id;";

    private final static String SELECT_EMPLOYEE_SQL = "SELECT * FROM employee WHERE employee_id=?";

    private final static String INSERT_EMPLOYEE_SQL=
            "INSERT INTO employee (employee_id, first_name, last_name, department_id, job_title, date_of_birth, gender) VALUES (?,?,?,?,?,?,?);";

    private final static String UPDATE_EMPLOYEE_SQL = "UPDATE employee SET first_name=?, last_name=?, department_id=?, job_title=?, date_of_birth=?, gender=? WHERE employee_id=?";

    private final static String DELETE_EMPLOYEE_SQL = "DELETE FROM employee WHERE employee_id=?";

    private RowMapper<Employee> employeeRowMapper=(rs,rowNum)->{
        Employee employee=new Employee();
        employee.setEmployeeId(rs.getInt("employee_id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setDepartment(departmentDao.getNameById(rs.getLong("department_id")));
        employee.setJobTitle(rs.getString("job_title"));
        employee.setDateOfBirth(rs.getDate("date_of_birth"));
        employee.setGender(Gender.valueOf(rs.getString("gender").toUpperCase()));
        return employee;
    };

    @Override
    public List<Employee> getAllEmployee() {
        return jdbcTemplate.query(SELECT_ALL_EMPLOYEE_SQL, employeeRowMapper);
    }

    @Override
    public Employee getById(int id) {
        return jdbcTemplate.queryForObject(SELECT_EMPLOYEE_SQL, employeeRowMapper, id);
    }

    @Override
    public void deleteEmployee(int id) {
        jdbcTemplate.update(DELETE_EMPLOYEE_SQL, id);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        jdbcTemplate.update(INSERT_EMPLOYEE_SQL, employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(), departmentDao.getIdByName(employee.getDepartment()), employee.getJobTitle(),  employee.getDateOfBirth(), employee.getGender().toString());
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        jdbcTemplate.update(UPDATE_EMPLOYEE_SQL, employee.getFirstName(), employee.getLastName(), departmentDao.getIdByName(employee.getDepartment()), employee.getJobTitle(), employee.getDateOfBirth(), employee.getGender().toString(), employee.getEmployeeId());
        return employee;
    }
}
