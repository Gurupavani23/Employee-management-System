package com.employee.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "leave_balance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    
    @Column(name = "leave_year", nullable = false)
    private Integer year;
    
    @Column(name = "sick_leave_balance")
    private Integer sickLeaveBalance = 10;
    
    @Column(name = "casual_leave_balance")
    private Integer casualLeaveBalance = 12;
    
    @Column(name = "annual_leave_balance")
    private Integer annualLeaveBalance = 15;
    
    @Column(name = "maternity_leave_balance")
    private Integer maternityLeaveBalance = 90;
    
    @Column(name = "paternity_leave_balance")
    private Integer paternityLeaveBalance = 7;
}
