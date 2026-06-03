package com.employee.system.controller;

import com.employee.system.dto.ApiResponse;
import com.employee.system.entity.Attendance;
import com.employee.system.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "*")
public class AttendanceController {
    
    @Autowired
    private AttendanceService attendanceService;
    
    @PostMapping
    public ResponseEntity<ApiResponse<Attendance>> markAttendance(@RequestBody Attendance attendance) {
        try {
            Attendance marked = attendanceService.markAttendance(attendance);
            return ResponseEntity.ok(ApiResponse.success("Attendance marked successfully", marked));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @PutMapping("/{id}/checkout")
    public ResponseEntity<ApiResponse<Attendance>> updateCheckOut(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime checkOutTime) {
        try {
            Attendance updated = attendanceService.updateCheckOut(id, checkOutTime);
            return ResponseEntity.ok(ApiResponse.success("Check-out time updated successfully", updated));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<ApiResponse<List<Attendance>>> getAttendanceByEmployee(@PathVariable Long employeeId) {
        List<Attendance> attendance = attendanceService.getAttendanceByEmployee(employeeId);
        return ResponseEntity.ok(ApiResponse.success("Attendance retrieved successfully", attendance));
    }
    
    @GetMapping("/employee/{employeeId}/range")
    public ResponseEntity<ApiResponse<List<Attendance>>> getAttendanceByDateRange(
            @PathVariable Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Attendance> attendance = attendanceService.getAttendanceByDateRange(employeeId, startDate, endDate);
        return ResponseEntity.ok(ApiResponse.success("Attendance retrieved successfully", attendance));
    }
    
    @GetMapping("/date/{date}")
    public ResponseEntity<ApiResponse<List<Attendance>>> getAttendanceByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Attendance> attendance = attendanceService.getAttendanceByDate(date);
        return ResponseEntity.ok(ApiResponse.success("Attendance retrieved successfully", attendance));
    }
}
