package com.employee.system.repository;

import com.employee.system.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
    Optional<Shift> findByShiftName(String shiftName);
    boolean existsByShiftName(String shiftName);
}
