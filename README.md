# Employee Data Handling System

A complete Employee Data Handling System built with **Java Spring Boot**, featuring employee management, attendance tracking, leave management, department administration, and a frontend login/dashboard.

## 🚀 Features

- **JWT Authentication** with login and secure REST endpoints
- **Employee Management** with create/read/update/delete operations
- **Attendance Tracking** for check-in/check-out records
- **Leave Management** with requests, approval, and balance tracking
- **Department Management** for organizing teams
- **Shift Management** for employee scheduling

## 🛠️ Tech Stack

- Java 21
- Spring Boot 3.2.0
- Spring Security
- Spring Data JPA
- H2 in-memory database
- JWT for token-based authentication
- Maven
- Vanilla HTML/CSS/JS frontend

## 📦 Project Structure

- `src/main/java` – backend application code
- `src/main/resources` – Spring Boot configuration
- `frontend` – static frontend UI for login and dashboard
- `pom.xml` – Maven build configuration

## ✅ Prerequisites

- Java 21 or newer
- Maven installed or use the included `mvnw` wrapper

## 🚀 Run the Backend

From the project root:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

On Windows, use:

```powershell
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

The backend starts on `http://localhost:8080`.

## 🌐 Run the Frontend

Open `frontend/index.html` directly in your browser, or launch the static frontend server if you need one.

If you have Python installed:

```bash
cd frontend
python -m http.server 3000
```

Then open `http://localhost:3000`.

## 🔧 Application Configuration

The app currently uses an H2 in-memory database configured in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:employee_db
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

You can access the H2 console at `http://localhost:8080/h2-console`.

## 👤 Default Login Credentials

| Username | Password | Role |
|----------|----------|------|
| admin | admin123 | ADMIN |
| manager | manager123 | MANAGER |
| employee | employee123 | EMPLOYEE |

## 📡 Main API Endpoints

### Authentication
- `POST /api/auth/login` – Login and receive a JWT token
- `POST /api/auth/register` – Register a new user

### Employees
- `GET /api/employees`
- `GET /api/employees/{id}`
- `POST /api/employees`
- `PUT /api/employees/{id}`
- `DELETE /api/employees/{id}`

### Departments
- `GET /api/departments`
- `GET /api/departments/{id}`
- `POST /api/departments`
- `PUT /api/departments/{id}`
- `DELETE /api/departments/{id}`

### Attendance
- `GET /api/attendance/employee/{employeeId}`
- `POST /api/attendance`
- `PUT /api/attendance/{id}/checkout`

### Leaves
- `GET /api/leaves/employee/{employeeId}`
- `POST /api/leaves`
- `PUT /api/leaves/{id}/approve`
- `PUT /api/leaves/{id}/reject`

## 🔐 How to Use the JWT Token

Include this header in protected requests:

```http
Authorization: Bearer YOUR_JWT_TOKEN
```

## 🧪 Quick cURL Example

Login:

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'
```

Create a department:

```bash
curl -X POST http://localhost:8080/api/departments \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{"departmentCode":"IT","name":"Information Technology","description":"IT Department"}'
```

## 📝 Notes

- The backend runs on port `8080`.
- The frontend can be served from `3000` or opened directly.
- This repository contains both backend and frontend files.

## 📁 GitHub

This repository is pushed to:

`https://github.com/Gurupavani23/Employee-management-System`

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
