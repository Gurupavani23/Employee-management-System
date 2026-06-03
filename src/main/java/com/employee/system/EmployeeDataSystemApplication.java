package com.employee.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeDataSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeDataSystemApplication.class, args);
        printStartupMessage();
    }
    
    private static void printStartupMessage() {
        System.out.println("\n" +
                "╔════════════════════════════════════════════════════════════╗\n" +
                "║  Employee Data Handling System - Started Successfully!    ║\n" +
                "╠════════════════════════════════════════════════════════════╣\n" +
                "║  Server URL:  http://localhost:8080                       ║\n" +
                "║  API Base:    http://localhost:8080/api                   ║\n" +
                "╠════════════════════════════════════════════════════════════╣\n" +
                "║  Default Users:                                           ║\n" +
                "║    Admin:    username: admin    password: admin123        ║\n" +
                "║    Manager:  username: manager  password: manager123      ║\n" +
                "║    Employee: username: employee password: employee123     ║\n" +
                "╠════════════════════════════════════════════════════════════╣\n" +
                "║  API Endpoints:                                           ║\n" +
                "║    POST /api/auth/login        - User login               ║\n" +
                "║    GET  /api/employees         - List all employees       ║\n" +
                "║    GET  /api/departments       - List all departments     ║\n" +
                "║    POST /api/attendance        - Mark attendance          ║\n" +
                "║    POST /api/leaves            - Create leave request     ║\n" +
                "╠════════════════════════════════════════════════════════════╣\n" +
                "║  Documentation:                                           ║\n" +
                "║    README.md            - Full documentation              ║\n" +
                "║    API_DOCUMENTATION.md - Complete API reference          ║\n" +
                "║    QUICK_START.md       - Quick setup guide               ║\n" +
                "╚════════════════════════════════════════════════════════════╝\n");
    }
}
