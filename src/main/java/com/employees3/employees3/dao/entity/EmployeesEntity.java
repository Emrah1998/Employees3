package com.employees3.employees3.dao.entity;

import com.employees3.employees3.model.enums.EmployeesStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "employees3")
@Builder
public class EmployeesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private LocalDate hireDate;
    private Integer salary;
    private String department;
    @Enumerated(EnumType.STRING)
    private EmployeesStatus status;
}
