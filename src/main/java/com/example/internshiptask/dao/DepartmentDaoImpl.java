package com.example.internshiptask.dao;

import com.example.internshiptask.dto.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final static String SELECT_DEPARTMENT_BY_ID_SQL="SELECT department_id, department_name from departments WHERE department_id=?";
    private final static String SELECT_DEPARTMENT_BY_NAME_SQL="select department_id, department_name from departments where department_name=?";

    private RowMapper<Department> rowMapper=(rs,rowNum)->{
        Department department=new Department();
        department.setDepartmentId(rs.getLong("department_id"));
        department.setDepartmentName(rs.getString("department_name"));
        return department;
    };

    @Override
    public String getNameById(Long id) {
        Department department= jdbcTemplate.queryForObject(SELECT_DEPARTMENT_BY_ID_SQL,rowMapper, id);
        return department.getDepartmentName();
    }

    @Override
    public Long getIdByName(String department_name) {
        Department department= jdbcTemplate.queryForObject(SELECT_DEPARTMENT_BY_NAME_SQL, rowMapper, department_name);
        return department.getDepartmentId();
    }
}
