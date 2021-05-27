package com.example.crudjdbcexample.dao;


import com.example.crudjdbcexample.models.Employee;
import com.example.crudjdbcexample.models.responses.UserEmployeePaginationResponse;

public interface EmployeeDao {
    Employee getEmployee(Long EmployeeId);

    void updateEmployeeDetails(Employee employee);

    UserEmployeePaginationResponse getEmployees(Long userId, int pageNumber, int pageSize, String search);

    void createEmployee(Employee employee);

    void deleteEmployee(Long employeeId);
}
