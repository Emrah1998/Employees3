package com.employees3.employees3.controller;

import com.employees3.employees3.model.request.CreateEmployeesRequest;
import com.employees3.employees3.model.request.UpdateSalaryRequest;
import com.employees3.employees3.model.response.EmployeesResponse;
import com.employees3.employees3.service.EmployeesServiceHandle;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/employees3")
public class EmployeesController {
    private final EmployeesServiceHandle employeesService;

    @PostMapping
    public void saveEmployees(@RequestBody CreateEmployeesRequest request) {
        employeesService.saveEmployees(request);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployees(@PathVariable Long id) {
        employeesService.deleteEmployees(id);
    }

    @PatchMapping("/{id}/salary")
    public void updateEmployeesSalary(@PathVariable Long id,
                                      @RequestBody UpdateSalaryRequest salary) {
        employeesService.updateEmployeesSalary(id,salary);
    }

    @GetMapping("/{id}")
    public EmployeesResponse getEmployees(@PathVariable Long id) {
        return employeesService.getEmployees(id);
    }

    @DeleteMapping("/cache")
    public void deleteCache(){
        employeesService.deleteCach();
    }

    @PutMapping("/cache")
    public EmployeesResponse updateCache(@RequestParam Long id){
        return employeesService.updateCache(id);
    }
}
