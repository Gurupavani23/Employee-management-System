package com.employee.system.service;

import com.employee.system.entity.Department;
import com.employee.system.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentService {
    
    @Autowired
    private DepartmentRepository departmentRepository;
    
    public Department createDepartment(Department department) {
        if (departmentRepository.existsByDepartmentCode(department.getDepartmentCode())) {
            throw new RuntimeException("Department code already exists");
        }
        return departmentRepository.save(department);
    }
    
    public Department updateDepartment(Long id, Department department) {
        Department existing = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        
        existing.setName(department.getName());
        existing.setDescription(department.getDescription());
        existing.setActive(department.getActive());
        
        return departmentRepository.save(existing);
    }
    
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }
    
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
    
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
