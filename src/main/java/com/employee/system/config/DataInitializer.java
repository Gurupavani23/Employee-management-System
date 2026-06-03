package com.employee.system.config;

import com.employee.system.entity.User;
import com.employee.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Create default admin user if not exists
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@company.com");
            admin.setFullName("System Administrator");
            admin.setRole(User.Role.ADMIN);
            admin.setActive(true);
            userRepository.save(admin);
            System.out.println("Default admin user created - Username: admin, Password: admin123");
        }
        
        // Create default manager user if not exists
        if (!userRepository.existsByUsername("manager")) {
            User manager = new User();
            manager.setUsername("manager");
            manager.setPassword(passwordEncoder.encode("manager123"));
            manager.setEmail("manager@company.com");
            manager.setFullName("Department Manager");
            manager.setRole(User.Role.MANAGER);
            manager.setActive(true);
            userRepository.save(manager);
            System.out.println("Default manager user created - Username: manager, Password: manager123");
        }
        
        // Create default employee user if not exists
        if (!userRepository.existsByUsername("employee")) {
            User employee = new User();
            employee.setUsername("employee");
            employee.setPassword(passwordEncoder.encode("employee123"));
            employee.setEmail("employee@company.com");
            employee.setFullName("Test Employee");
            employee.setRole(User.Role.EMPLOYEE);
            employee.setActive(true);
            userRepository.save(employee);
            System.out.println("Default employee user created - Username: employee, Password: employee123");
        }
    }
}
