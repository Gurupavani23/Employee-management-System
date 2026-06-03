package com.employee.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;
    
    @Column(unique = true, nullable = false)
    private String employeeCode;
    
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    
    @Column(nullable = false)
    private String designation;
    
    @Column(name = "date_of_joining")
    private LocalDate dateOfJoining;
    
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    private String phoneNumber;
    
    private String address;
    
    @Column(nullable = false)
    private Double salary;
    
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;
}
