package com.example.crudjdbcexample.mapper;

import com.example.crudjdbcexample.models.responses.EmployeeResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class EmployeeResponseMapper implements RowMapper<EmployeeResponse> {
    public EmployeeResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String email = rs.getString("email");
        String name = rs.getString("first_name");
        String surname = rs.getString("last_name");
        Timestamp deleted_at = rs.getTimestamp("deleted_at");
        Timestamp created_at = rs.getTimestamp("created_at");
        Timestamp updated_at = rs.getTimestamp("updated_at");

        return new EmployeeResponse(id, name, surname, email);
    }
}
