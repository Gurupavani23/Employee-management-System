package com.employee.system.service;

import com.employee.system.entity.Attendance;
import com.employee.system.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AttendanceService {
    
    @Autowired
    private AttendanceRepository attendanceRepository;
    
    public Attendance markAttendance(Attendance attendance) {
        // Check if attendance already exists for the date
        attendanceRepository.findByEmployeeIdAndDate(attendance.getEmployee().getId(), attendance.getDate())
                .ifPresent(existing -> {
                    throw new RuntimeException("Attendance already marked for this date");
                });
        
        // Calculate working hours if both check-in and check-out times are present
        if (attendance.getCheckInTime() != null && attendance.getCheckOutTime() != null) {
            Duration duration = Duration.between(attendance.getCheckInTime(), attendance.getCheckOutTime());
            attendance.setWorkingHours(duration.toMinutes() / 60.0);
        }
        
        return attendanceRepository.save(attendance);
    }
    
    public Attendance updateCheckOut(Long attendanceId, LocalTime checkOutTime) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));
        
        attendance.setCheckOutTime(checkOutTime);
        
        if (attendance.getCheckInTime() != null) {
            Duration duration = Duration.between(attendance.getCheckInTime(), checkOutTime);
            attendance.setWorkingHours(duration.toMinutes() / 60.0);
        }
        
        return attendanceRepository.save(attendance);
    }
    
    public List<Attendance> getAttendanceByEmployee(Long employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }
    
    public List<Attendance> getAttendanceByDateRange(Long employeeId, LocalDate startDate, LocalDate endDate) {
        return attendanceRepository.findByEmployeeIdAndDateBetween(employeeId, startDate, endDate);
    }
    
    public List<Attendance> getAttendanceByDate(LocalDate date) {
        return attendanceRepository.findByDate(date);
    }
}
