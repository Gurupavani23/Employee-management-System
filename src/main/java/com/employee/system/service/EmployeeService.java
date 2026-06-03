package com.employee.system.service;

import com.employee.system.entity.Employee;
import com.employee.system.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    public Employee createEmployee(Employee employee) {
        if (employeeRepository.existsByEmployeeCode(employee.getEmployeeCode())) {
            throw new RuntimeException("Employee code already exists");
        }
        return employeeRepository.save(employee);
    }
    
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        
        existingEmployee.setDesignation(employee.getDesignation());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setPhoneNumber(employee.getPhoneNumber());
        existingEmployee.setAddress(employee.getAddress());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setManager(employee.getManager());
        
        return employeeRepository.save(existingEmployee);
    }
    
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }
    
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    public List<Employee> getEmployeesByDepartment(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }
    
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
