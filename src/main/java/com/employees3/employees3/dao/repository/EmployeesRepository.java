package com.employees3.employees3.dao.repository;

import com.employees3.employees3.dao.entity.EmployeesEntity;
import com.employees3.employees3.model.enums.EmployeesStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeesRepository extends JpaRepository<EmployeesEntity,Long> {

    Optional<EmployeesEntity> findByIdAndStatusNot(Long id, EmployeesStatus status);
}
