package com.employee.system.controller;

import com.employee.system.dto.ApiResponse;
import com.employee.system.entity.LeaveBalance;
import com.employee.system.entity.LeaveRequest;
import com.employee.system.entity.User;
import com.employee.system.repository.UserRepository;
import com.employee.system.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@CrossOrigin(origins = "*")
public class LeaveController {
    
    @Autowired
    private LeaveService leaveService;
    
    @Autowired
    private UserRepository userRepository;
    
    @PostMapping
    public ResponseEntity<ApiResponse<LeaveRequest>> createLeaveRequest(@RequestBody LeaveRequest leaveRequest) {
        try {
            LeaveRequest created = leaveService.createLeaveRequest(leaveRequest);
            return ResponseEntity.ok(ApiResponse.success("Leave request created successfully", created));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @PutMapping("/{id}/approve")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ApiResponse<LeaveRequest>> approveLeaveRequest(
            @PathVariable Long id,
            @RequestParam String remarks,
            Authentication authentication) {
        try {
            User approver = userRepository.findByUsername(authentication.getName())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            LeaveRequest approved = leaveService.approveLeaveRequest(id, approver, remarks);
            return ResponseEntity.ok(ApiResponse.success("Leave request approved successfully", approved));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @PutMapping("/{id}/reject")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ApiResponse<LeaveRequest>> rejectLeaveRequest(
            @PathVariable Long id,
            @RequestParam String remarks,
            Authentication authentication) {
        try {
            User approver = userRepository.findByUsername(authentication.getName())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            LeaveRequest rejected = leaveService.rejectLeaveRequest(id, approver, remarks);
            return ResponseEntity.ok(ApiResponse.success("Leave request rejected successfully", rejected));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<ApiResponse<List<LeaveRequest>>> getLeaveRequestsByEmployee(@PathVariable Long employeeId) {
        List<LeaveRequest> requests = leaveService.getLeaveRequestsByEmployee(employeeId);
        return ResponseEntity.ok(ApiResponse.success("Leave requests retrieved successfully", requests));
    }
    
    @GetMapping("/pending")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ApiResponse<List<LeaveRequest>>> getPendingLeaveRequests() {
        List<LeaveRequest> requests = leaveService.getPendingLeaveRequests();
        return ResponseEntity.ok(ApiResponse.success("Pending leave requests retrieved successfully", requests));
    }
    
    @GetMapping("/balance/{employeeId}/{year}")
    public ResponseEntity<ApiResponse<LeaveBalance>> getLeaveBalance(
            @PathVariable Long employeeId,
            @PathVariable Integer year) {
        LeaveBalance balance = leaveService.getLeaveBalance(employeeId, year);
        return ResponseEntity.ok(ApiResponse.success("Leave balance retrieved successfully", balance));
    }
}
