# Employee Data Handling System

A complete Employee Data Handling System built with **Java Spring Boot**, featuring employee management, attendance tracking, leave management, and department administration.

## 🚀 Features

### Core Modules
- **User Authentication** - JWT-based secure authentication with role-based access control
- **Employee Management** - Complete CRUD operations for employee data
- **Attendance System** - Track employee check-in/check-out times and working hours
- **Leave Management** - Request, approve, and track employee leaves with balance management
- **Department Management** - Organize employees by departments
- **Shift Management** - Assign and track employee shifts

### Security Features
- JWT (JSON Web Token) authentication
- Role-based authorization (ADMIN, MANAGER, EMPLOYEE)
- Password encryption using BCrypt
- Secure REST API endpoints

## 🛠️ Technology Stack

- **Java 21**
- **Spring Boot 3.2.0**
- **Spring Security** - Authentication & Authorization
- **Spring Data JPA** - Database operations
- **MySQL** - Database
- **JWT** - Token-based authentication
- **Lombok** - Reduce boilerplate code
- **Maven** - Build tool

## 📋 Prerequisites

Before running this project, ensure you have:

1. **Java 21** or higher installed
   ```bash
   java -version
   ```

2. **MySQL Server** running on localhost:3306
   ```bash
   mysql --version
   ```

3. **Maven** (comes with the project as mvnw)

## 🔧 Setup Instructions

### 1. Clone or Navigate to Project Directory
```bash
cd "d:\Mtech\Project Java\employee-data-system"
```

### 2. Configure Database

Create a MySQL database:
```sql
CREATE DATABASE employee_db;
```

**Or** the application will auto-create it on first run due to `createDatabaseIfNotExist=true` parameter.

### 3. Update Database Credentials

Edit `src/main/resources/application.properties` if needed:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=root
```

### 4. Build the Project
```bash
mvnw clean install
```

### 5. Run the Application
```bash
mvnw spring-boot:run
```

Or run the JAR file:
```bash
java -jar target/employee-data-system-1.0.0.jar
```

The application will start on **http://localhost:8080**

## 👤 Default Users

The system creates default users automatically on first startup:

| Username | Password | Role | Email |
|----------|----------|------|-------|
| admin | admin123 | ADMIN | admin@company.com |
| manager | manager123 | MANAGER | manager@company.com |
| employee | employee123 | EMPLOYEE | employee@company.com |

## 📡 API Endpoints

### Authentication APIs (`/api/auth`)
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - User login (returns JWT token)

### Employee APIs (`/api/employees`)
- `POST /api/employees` - Create employee (ADMIN/MANAGER)
- `GET /api/employees` - Get all employees
- `GET /api/employees/{id}` - Get employee by ID
- `PUT /api/employees/{id}` - Update employee (ADMIN/MANAGER)
- `DELETE /api/employees/{id}` - Delete employee (ADMIN only)
- `GET /api/employees/department/{departmentId}` - Get employees by department

### Attendance APIs (`/api/attendance`)
- `POST /api/attendance` - Mark attendance
- `PUT /api/attendance/{id}/checkout` - Update checkout time
- `GET /api/attendance/employee/{employeeId}` - Get employee attendance
- `GET /api/attendance/employee/{employeeId}/range` - Get attendance by date range
- `GET /api/attendance/date/{date}` - Get all attendance for a date

### Leave Management APIs (`/api/leaves`)
- `POST /api/leaves` - Create leave request
- `PUT /api/leaves/{id}/approve` - Approve leave (ADMIN/MANAGER)
- `PUT /api/leaves/{id}/reject` - Reject leave (ADMIN/MANAGER)
- `GET /api/leaves/employee/{employeeId}` - Get employee's leave requests
- `GET /api/leaves/pending` - Get pending leave requests (ADMIN/MANAGER)
- `GET /api/leaves/balance/{employeeId}/{year}` - Get leave balance

### Department APIs (`/api/departments`)
- `POST /api/departments` - Create department (ADMIN)
- `GET /api/departments` - Get all departments
- `GET /api/departments/{id}` - Get department by ID
- `PUT /api/departments/{id}` - Update department (ADMIN)
- `DELETE /api/departments/{id}` - Delete department (ADMIN)

## 🔐 Authentication Flow

### 1. Login Request
```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```

### 2. Login Response
```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "username": "admin",
    "role": "ADMIN",
    "fullName": "System Administrator"
  }
}
```

### 3. Using the Token
Add the JWT token to all subsequent requests:
```http
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

## 📊 Database Schema

### Main Tables
- **users** - User authentication and roles
- **employees** - Employee details
- **departments** - Department information
- **attendance** - Daily attendance records
- **leave_requests** - Leave applications
- **leave_balance** - Available leave balance per employee
- **shifts** - Shift timings
- **employee_shifts** - Employee shift assignments

## 🎯 Testing the Application

### Using cURL

**Login:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'
```

**Create Department (with token):**
```bash
curl -X POST http://localhost:8080/api/departments \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN_HERE" \
  -d '{"departmentCode":"IT","name":"Information Technology","description":"IT Department"}'
```

### Using Postman

1. Import the API collection
2. Set base URL: `http://localhost:8080`
3. Login to get JWT token
4. Add token to Authorization header for protected endpoints

## 🔄 Application Flow

1. **Admin/Manager** logs in
2. **Admin** creates departments
3. **Admin/Manager** creates employee records
4. **Employees** mark daily attendance
5. **Employees** apply for leaves
6. **Manager/Admin** approves/rejects leave requests
7. System automatically deducts leave balance on approval

## 🛡️ Security Configuration

- **Public endpoints:** `/api/auth/**`
- **ADMIN only:** Delete operations, department management
- **ADMIN/MANAGER:** Employee CRUD, leave approvals
- **All authenticated users:** View operations, attendance, leave requests

## 📝 Leave Types

- **SICK_LEAVE** - 10 days per year
- **CASUAL_LEAVE** - 12 days per year
- **ANNUAL_LEAVE** - 15 days per year
- **MATERNITY_LEAVE** - 90 days
- **PATERNITY_LEAVE** - 7 days
- **UNPAID_LEAVE** - No balance limit

## 🐛 Troubleshooting

### Port Already in Use
Change port in `application.properties`:
```properties
server.port=8081
```

### Database Connection Failed
- Ensure MySQL is running
- Verify credentials in `application.properties`
- Create database manually if auto-creation fails

### JWT Token Expired
- Default expiration: 24 hours (86400000 ms)
- Login again to get a new token

## 📈 Future Enhancements

- Email notifications for leave approvals
- Payroll integration
- Performance appraisal module
- Report generation (PDF/Excel)
- Mobile application support
- Real-time notifications

## 👨‍💻 Development

### Project Structure
```
employee-data-system/
├── src/main/java/com/employee/system/
│   ├── config/          # Security & app configuration
│   ├── controller/      # REST controllers
│   ├── dto/            # Data Transfer Objects
│   ├── entity/         # JPA entities
│   ├── repository/     # Data repositories
│   ├── security/       # JWT & security components
│   └── service/        # Business logic
├── src/main/resources/
│   └── application.properties
└── pom.xml
```

## 📄 License

This project is created for educational purposes.

## 🤝 Contributing

Feel free to fork and enhance the project!

## 📧 Contact

For issues or questions, please create an issue in the repository.

---
**Made with ❤️ using Spring Boot**
