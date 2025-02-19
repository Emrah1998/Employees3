package com.employees3.employees3.service;

import com.employees3.employees3.model.request.CreateEmployeesRequest;
import com.employees3.employees3.model.request.UpdateSalaryRequest;
import com.employees3.employees3.model.response.EmployeesResponse;

public interface EmployeesService {
    void saveEmployees(CreateEmployeesRequest request);
    void deleteEmployees(Long id);
    void updateEmployeesSalary(Long id, UpdateSalaryRequest salary);
    EmployeesResponse getEmployees(Long id);
}
