package com.example.crudjdbcexample.models.responses;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmployeeResponse {
    private Long id;
    private String name;
    private String surname;
    private String email;
}
