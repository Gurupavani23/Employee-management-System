package com.employee.system.repository;

import com.employee.system.entity.EmployeeShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeShiftRepository extends JpaRepository<EmployeeShift, Long> {
    List<EmployeeShift> findByEmployeeId(Long employeeId);
    List<EmployeeShift> findByShiftId(Long shiftId);
    List<EmployeeShift> findByEmployeeIdAndActiveTrue(Long employeeId);
}
