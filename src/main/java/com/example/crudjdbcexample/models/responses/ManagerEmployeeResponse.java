package com.example.crudjdbcexample.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ManagerEmployeeResponse {
    private Long id;
    private String email;
    private String name;
    private String surname;
}