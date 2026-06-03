package com.employee.system.service;

import com.employee.system.entity.LeaveBalance;
import com.employee.system.entity.LeaveRequest;
import com.employee.system.entity.User;
import com.employee.system.repository.LeaveBalanceRepository;
import com.employee.system.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LeaveService {
    
    @Autowired
    private LeaveRequestRepository leaveRequestRepository;
    
    @Autowired
    private LeaveBalanceRepository leaveBalanceRepository;
    
    public LeaveRequest createLeaveRequest(LeaveRequest leaveRequest) {
        // Calculate number of days
        long days = ChronoUnit.DAYS.between(leaveRequest.getStartDate(), leaveRequest.getEndDate()) + 1;
        leaveRequest.setNumberOfDays((int) days);
        leaveRequest.setStatus(LeaveRequest.Status.PENDING);
        
        return leaveRequestRepository.save(leaveRequest);
    }
    
    public LeaveRequest approveLeaveRequest(Long leaveRequestId, User approver, String remarks) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));
        
        if (leaveRequest.getStatus() != LeaveRequest.Status.PENDING) {
            throw new RuntimeException("Leave request is already processed");
        }
        
        // Check leave balance
        int currentYear = LocalDateTime.now().getYear();
        LeaveBalance leaveBalance = leaveBalanceRepository
                .findByEmployeeIdAndYear(leaveRequest.getEmployee().getId(), currentYear)
                .orElseThrow(() -> new RuntimeException("Leave balance not found"));
        
        // Deduct from appropriate leave balance
        switch (leaveRequest.getLeaveType()) {
            case SICK_LEAVE:
                if (leaveBalance.getSickLeaveBalance() < leaveRequest.getNumberOfDays()) {
                    throw new RuntimeException("Insufficient sick leave balance");
                }
                leaveBalance.setSickLeaveBalance(leaveBalance.getSickLeaveBalance() - leaveRequest.getNumberOfDays());
                break;
            case CASUAL_LEAVE:
                if (leaveBalance.getCasualLeaveBalance() < leaveRequest.getNumberOfDays()) {
                    throw new RuntimeException("Insufficient casual leave balance");
                }
                leaveBalance.setCasualLeaveBalance(leaveBalance.getCasualLeaveBalance() - leaveRequest.getNumberOfDays());
                break;
            case ANNUAL_LEAVE:
                if (leaveBalance.getAnnualLeaveBalance() < leaveRequest.getNumberOfDays()) {
                    throw new RuntimeException("Insufficient annual leave balance");
                }
                leaveBalance.setAnnualLeaveBalance(leaveBalance.getAnnualLeaveBalance() - leaveRequest.getNumberOfDays());
                break;
        }
        
        leaveBalanceRepository.save(leaveBalance);
        
        leaveRequest.setStatus(LeaveRequest.Status.APPROVED);
        leaveRequest.setApprovedBy(approver);
        leaveRequest.setApprovedAt(LocalDateTime.now());
        leaveRequest.setApprovalRemarks(remarks);
        
        return leaveRequestRepository.save(leaveRequest);
    }
    
    public LeaveRequest rejectLeaveRequest(Long leaveRequestId, User approver, String remarks) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));
        
        if (leaveRequest.getStatus() != LeaveRequest.Status.PENDING) {
            throw new RuntimeException("Leave request is already processed");
        }
        
        leaveRequest.setStatus(LeaveRequest.Status.REJECTED);
        leaveRequest.setApprovedBy(approver);
        leaveRequest.setApprovedAt(LocalDateTime.now());
        leaveRequest.setApprovalRemarks(remarks);
        
        return leaveRequestRepository.save(leaveRequest);
    }
    
    public List<LeaveRequest> getLeaveRequestsByEmployee(Long employeeId) {
        return leaveRequestRepository.findByEmployeeId(employeeId);
    }
    
    public List<LeaveRequest> getPendingLeaveRequests() {
        return leaveRequestRepository.findByStatus(LeaveRequest.Status.PENDING);
    }
    
    public LeaveBalance getLeaveBalance(Long employeeId, Integer year) {
        return leaveBalanceRepository.findByEmployeeIdAndYear(employeeId, year)
                .orElse(null);
    }
}
