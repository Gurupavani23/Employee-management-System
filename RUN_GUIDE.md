# How to Run the Complete Employee Data Handling System

## Quick Start Guide

This guide will help you run both the **backend (Java Spring Boot)** and **frontend (HTML/CSS/JavaScript)** of the Employee Data Handling System.

---

## Prerequisites

### For Backend
- **Java 21** (JDK 21)
- **Maven 3.8+** (or use included Maven Wrapper)
- **MySQL 8.0+** (or use H2 in-memory database)
- **IDE** (IntelliJ IDEA, Eclipse, or VS Code with Java extensions)

### For Frontend
- **Modern Web Browser** (Chrome, Firefox, Edge, Safari)
- **HTTP Server** (Python, Node.js, or VS Code Live Server)

---

## Step 1: Start the Backend

### Option A: Using IntelliJ IDEA (Recommended)
1. Open IntelliJ IDEA
2. Go to **File → Open** and select the project directory
3. Wait for Maven to download dependencies
4. Right-click on `EmployeeDataSystemApplication.java`
5. Select **Run 'EmployeeDataSystemApplication'**
6. Backend will start on http://localhost:8080

### Option B: Using Command Line with Maven
```bash
cd employee-data-system
mvn clean install
mvn spring-boot:run
```

### Option C: Using Maven Wrapper (No Maven Installation Required)
```bash
cd employee-data-system
./mvnw.cmd clean install    # Windows
./mvnw clean install        # Linux/Mac
./mvnw.cmd spring-boot:run  # Windows
./mvnw spring-boot:run      # Linux/Mac
```

### Option D: Using the Batch Script (Windows)
```bash
cd employee-data-system
run.bat
```

### Database Configuration

#### Using MySQL (Default)
1. Install MySQL 8.0+
2. Create database:
```sql
CREATE DATABASE emp_mgmt_sys_db;
```
3. Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/emp_mgmt_sys_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

#### Using H2 In-Memory Database (No MySQL Required)
1. Rename `application.properties` to `application.properties.bak`
2. Rename `application-h2.properties` to `application.properties`
3. Run the application - H2 will auto-create the database

### Verify Backend is Running
Open your browser and go to:
- http://localhost:8080 (should show default message)
- Backend REST API is accessible at: http://localhost:8080/api/

---

## Step 2: Start the Frontend

Navigate to the frontend directory:
```bash
cd employee-data-system/frontend
```

### Option A: Using Python HTTP Server (Recommended)
```bash
python -m http.server 3000
```
Then open: http://localhost:3000

### Option B: Using Node.js http-server
```bash
npm install -g http-server
http-server -p 3000
```
Then open: http://localhost:3000

### Option C: Using VS Code Live Server Extension
1. Install "Live Server" extension in VS Code
2. Open `frontend` folder in VS Code
3. Right-click on `index.html`
4. Select "Open with Live Server"
5. Browser will auto-open at http://127.0.0.1:5500

### Option D: Using PHP Built-in Server
```bash
php -S localhost:3000
```

### Verify Frontend is Running
- Login page should appear at http://localhost:3000
- No errors in browser console (press F12 to check)

---

## Step 3: Login and Explore

### Default User Accounts

The system comes with three pre-configured user accounts:

#### Admin Account
- **Username:** `admin`
- **Password:** `admin123`
- **Access:** Full system administration
  - Manage employees
  - Manage departments
  - View all attendance records
  - Approve/reject leave requests
  - Generate reports

#### Manager Account
- **Username:** `manager`
- **Password:** `manager123`
- **Access:** Team management
  - View team members
  - Approve/reject team leave requests
  - View team attendance
  - Generate team reports

#### Employee Account
- **Username:** `employee`
- **Password:** `employee123`
- **Access:** Self-service
  - View personal profile
  - Mark own attendance
  - Apply for leave
  - View leave balance

---

## Step 4: Test the System

### Test Employee Management
1. Login as **admin** (admin / admin123)
2. Navigate to **Employees** page
3. Click **+ Add Employee** button
4. Fill in employee details:
   - First Name: John
   - Last Name: Doe
   - Email: john.doe@company.com
   - Phone: 123-456-7890
   - Hire Date: Today's date
   - Salary: 50000
   - Department: (select from dropdown)
5. Click **Save**
6. New employee should appear in the table

### Test Department Management
1. Navigate to **Departments** page
2. Click **+ Add Department**
3. Fill in department details:
   - Department Code: IT
   - Name: Information Technology
   - Description: IT Department
   - Status: Active
4. Click **Save**

### Test Attendance Tracking
1. Navigate to **Attendance** page
2. Click **+ Mark Attendance**
3. Select employee, date, and times
4. Mark status as "Present"
5. Save attendance record

### Test Leave Management
1. Navigate to **Leave Management** page
2. Click **+ Apply Leave**
3. Select employee and leave type
4. Choose start and end dates
5. Enter reason
6. Submit leave request
7. Approve or reject from pending requests table

---

## Architecture Overview

```
┌─────────────────────────────────────────────────────────┐
│                  Frontend (Port 3000)                   │
│  HTML5 + CSS3 + Vanilla JavaScript + Fetch API          │
│  ├── Login Page                                          │
│  ├── Admin Dashboard                                     │
│  ├── Employee Management                                 │
│  ├── Department Management                               │
│  ├── Attendance Tracking                                 │
│  └── Leave Management                                    │
└──────────────────┬──────────────────────────────────────┘
                   │ HTTP REST API Calls (JWT Auth)
                   ▼
┌─────────────────────────────────────────────────────────┐
│                  Backend (Port 8080)                    │
│        Java 21 + Spring Boot 3.2.0 + MySQL              │
│  ├── Security Layer (JWT Authentication)                │
│  ├── REST Controllers                                    │
│  ├── Service Layer (Business Logic)                     │
│  ├── Repository Layer (Data Access)                     │
│  └── Entity Layer (JPA Models)                          │
└──────────────────┬──────────────────────────────────────┘
                   │ JDBC
                   ▼
┌─────────────────────────────────────────────────────────┐
│               Database (Port 3306)                      │
│        MySQL 8.0+ or H2 In-Memory Database              │
└─────────────────────────────────────────────────────────┘
```

---

## API Endpoints Overview

### Authentication
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - Register new user

### Employees
- `GET /api/employees` - Get all employees
- `POST /api/employees` - Create new employee
- `PUT /api/employees/{id}` - Update employee
- `DELETE /api/employees/{id}` - Delete employee

### Departments
- `GET /api/departments` - Get all departments
- `POST /api/departments` - Create department
- `PUT /api/departments/{id}` - Update department
- `DELETE /api/departments/{id}` - Delete department

### Attendance
- `POST /api/attendance` - Mark attendance
- `GET /api/attendance/date/{date}` - Get attendance by date
- `GET /api/attendance/employee/{id}` - Get employee attendance

### Leaves
- `POST /api/leaves` - Create leave request
- `GET /api/leaves/pending` - Get pending leaves
- `PUT /api/leaves/{id}/approve` - Approve leave
- `PUT /api/leaves/{id}/reject` - Reject leave

For complete API documentation, see `API_DOCUMENTATION.md`.

---

## Troubleshooting

### Backend Issues

#### Problem: "Port 8080 already in use"
**Solution:** Kill the process using port 8080 or change port in `application.properties`:
```properties
server.port=8081
```

#### Problem: "Unable to connect to MySQL"
**Solution:**
1. Verify MySQL is running: `mysql -u root -p`
2. Check database exists: `SHOW DATABASES;`
3. Update credentials in `application.properties`
4. Or switch to H2 database (see Database Configuration above)

#### Problem: "ClassNotFoundException: JAXB"
**Solution:** Make sure you're using Java 21 (not Java 8 or 11)

### Frontend Issues

#### Problem: "CORS Error" in browser console
**Solution:** Verify backend `SecurityConfig.java` has CORS enabled:
```java
@CrossOrigin(origins = "http://localhost:3000")
```

#### Problem: "401 Unauthorized" on all API calls
**Solution:**
1. Check if backend is running
2. Verify login credentials (admin/admin123)
3. Clear browser localStorage and login again
4. Check if JWT token is present in localStorage (F12 → Application → Local Storage)

#### Problem: Pages stuck on "Loading..."
**Solution:**
1. Open browser console (F12)
2. Check for JavaScript errors
3. Verify `config.js` has correct `BASE_URL`: http://localhost:8080/api
4. Ensure backend is accessible

#### Problem: Login page doesn't redirect
**Solution:**
1. Check browser console for errors
2. Verify response contains JWT token
3. Check `auth.js` for redirect logic

---

## Project File Structure

```
employee-data-system/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/company/
│   │   │   │   ├── controller/      # REST Controllers
│   │   │   │   ├── service/         # Business Logic
│   │   │   │   ├── repository/      # Data Access
│   │   │   │   ├── entity/          # JPA Entities
│   │   │   │   ├── security/        # JWT Security
│   │   │   │   └── config/          # Configuration
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/                    # Unit Tests
│   ├── pom.xml                      # Maven dependencies
│   └── mvnw.cmd                     # Maven Wrapper
├── frontend/
│   ├── index.html                   # Login page
│   ├── css/
│   │   └── style.css               # All styles
│   ├── js/
│   │   ├── config.js               # API config
│   │   ├── api.js                  # API service
│   │   ├── auth.js                 # Auth logic
│   │   ├── admin-dashboard.js      # Dashboard
│   │   ├── employees.js            # Employee CRUD
│   │   ├── departments.js          # Department CRUD
│   │   ├── attendance.js           # Attendance tracking
│   │   └── leaves.js               # Leave management
│   └── pages/
│       ├── admin-dashboard.html
│       ├── employees.html
│       ├── departments.html
│       ├── attendance.html
│       └── leaves.html
├── README.md                        # Main documentation
├── API_DOCUMENTATION.md             # API reference
├── FRONTEND_GUIDE.md                # Frontend guide
└── RUN_GUIDE.md                     # This file
```

---

## Production Deployment

### Backend Deployment
1. Package application:
```bash
mvn clean package
```
2. Deploy `target/employee-data-system-0.0.1-SNAPSHOT.jar` to your server
3. Run with:
```bash
java -jar employee-data-system-0.0.1-SNAPSHOT.jar
```

### Frontend Deployment
1. Upload frontend files to web server (Apache, Nginx, etc.)
2. Update `config.js` with production API URL
3. Configure web server to serve static files
4. Set up SSL certificate for HTTPS

### Environment Variables (Production)
```bash
# Backend
SPRING_DATASOURCE_URL=jdbc:mysql://prod-server:3306/emp_db
SPRING_DATASOURCE_USERNAME=prod_user
SPRING_DATASOURCE_PASSWORD=prod_password
JWT_SECRET=your-production-secret-key

# Frontend
API_BASE_URL=https://api.yourcompany.com
```

---

## Support & Documentation

### Documentation Files
- `README.md` - Project overview and setup
- `API_DOCUMENTATION.md` - Complete API reference
- `FRONTEND_GUIDE.md` - Frontend development guide
- `QUICK_START.md` - Quick setup instructions
- `RUN_GUIDE.md` - This comprehensive run guide

### Getting Help
1. Check documentation files
2. Review error messages in console/logs
3. Verify all prerequisites are installed
4. Check port availability (8080, 3000)
5. Ensure all services are running

---

## Next Steps

After successfully running the system:

1. **Explore Features** - Try all CRUD operations
2. **Test Different Roles** - Login as admin, manager, and employee
3. **Add Test Data** - Create departments, employees, attendance records
4. **Review Code** - Understand the architecture and implementation
5. **Customize** - Modify styling, add new features, enhance functionality

---

## System Requirements Summary

| Component | Minimum | Recommended |
|-----------|---------|-------------|
| Java | JDK 21 | JDK 21 |
| Maven | 3.8+ | 3.9+ |
| MySQL | 8.0+ | 8.0+ |
| RAM | 2 GB | 4 GB |
| Disk Space | 500 MB | 1 GB |
| Browser | Chrome 90+ | Chrome/Firefox Latest |

---

**Ready to go!** Start the backend, launch the frontend, and login with admin/admin123 to begin. 🚀
