package com.employees3.employees3.service;

import com.employees3.employees3.dao.entity.EmployeesEntity;
import com.employees3.employees3.dao.entity.EmployeesFilesEntity;
import com.employees3.employees3.dao.repository.EmployeesFilesRepository;
import com.employees3.employees3.dao.repository.EmployeesRepository;
import com.employees3.employees3.exception.NotFoundException;
import com.employees3.employees3.model.enums.EmployeesStatus;
import com.employees3.employees3.model.request.CreateEmployeesRequest;
import com.employees3.employees3.model.request.UpdateSalaryRequest;
import com.employees3.employees3.model.response.EmployeesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.employees3.employees3.exception.ExceptionConstant.*;
import static com.employees3.employees3.model.enums.EmployeesStatus.INACTIVE;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeesServiceHandle implements EmployeesService{

    private final EmployeesRepository employeesRepository;
    private final EmployeesFilesRepository employeesFilesRepository;



    @Override
    @Transactional
    public void saveEmployees(CreateEmployeesRequest request) {
        employeesRepository.save(
                EmployeesEntity.builder()
                        .name(request.getName())
                        .surname(request.getSurname())
                        .hireDate(LocalDate.now())
                        .salary(request.getSalary())
                        .status(EmployeesStatus.ACTIVE)
                        .build()
        );
        employeesFilesRepository.save(EmployeesFilesEntity.builder()
                        .fileName("IT DEPARTMENT")
                .build());
        saveEmployeesToFile(); //Tranzaksiyani yoxladim isleyir
    }
    private void saveEmployeesToFile(){
        throw new RuntimeException("The employee was not saved to the file.");
    }

    @Override
    public void deleteEmployees(Long id) {
        var employees = fetchEmployeesIfExist(id);
        employees.setStatus(INACTIVE);
        employeesRepository.save(employees);
    }

    @Override
    public void updateEmployeesSalary(Long id, UpdateSalaryRequest salary) {
        var employees = fetchEmployeesIfExist(id);
        employees.setSalary(salary.getSalary());
        employeesRepository.save(employees);
    }
    @Cacheable("getEmployees")
    @Override
    public EmployeesResponse getEmployees(Long id) {
        var employees = fetchEmployeesIfExist(id);
        return new EmployeesResponse(employees.getName(),employees.getSurname(),employees.getHireDate(),employees.getSalary(), employees.getDepartment());
    }
    @CacheEvict(value = "getEmployees",allEntries = true)
    public void deleteCach(){
    }
    @CachePut("getEmployees")
    public EmployeesResponse updateCache(Long id){
        return getEmployees(id);
    }

    private EmployeesEntity fetchEmployeesIfExist(Long id) {
        return employeesRepository.findByIdAndStatusNot(id, INACTIVE)
                .orElseThrow(()-> {log.error("EmployeesLog.fetchEmployeesIfExist.error employee with id: {} not found", id);
                    return new NotFoundException(String.format(EMPLOYEE_NOT_FOUND_MESSAGE,id),
                            EMPLOYEE_NOT_FOUND_CODE);
                });
    }
}
