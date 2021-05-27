package com.example.crudjdbcexample.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UserEmployeePaginationResponse {

    List<ManagerEmployeeResponse> employees;

    long countOfPages;
    long totalCountOfEmployees;
}
