package com.example.crudjdbcexample.mapper;

import com.example.crudjdbcexample.models.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("first_name");
        String surname = rs.getString("last_name");
        String email = rs.getString("email");

        return new Employee(id, name, surname, email);
    }
}