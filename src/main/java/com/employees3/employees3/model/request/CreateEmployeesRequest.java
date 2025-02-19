package com.employees3.employees3.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeesRequest {
    private String name;
    private String surname;
    private Integer salary;
}
