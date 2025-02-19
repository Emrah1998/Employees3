package com.employees3.employees3.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesResponse {
    private String name;
    private String surname;
    private LocalDate hireDate;
    private Integer salary;
    private String department;
}
