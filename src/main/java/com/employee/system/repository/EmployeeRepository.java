package com.employee.system.repository;

import com.employee.system.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmployeeCode(String employeeCode);
    List<Employee> findByDepartmentId(Long departmentId);
    List<Employee> findByManagerId(Long managerId);
    boolean existsByEmployeeCode(String employeeCode);
}
