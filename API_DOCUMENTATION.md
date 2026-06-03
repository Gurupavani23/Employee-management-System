# Employee Data Handling System - API Documentation

## Base URL
```
http://localhost:8080
```

## Authentication
All protected endpoints require JWT token in Authorization header:
```
Authorization: Bearer <your_jwt_token>
```

---

## 1. Authentication APIs

### 1.1 Register User
**Endpoint:** `POST /api/auth/register`

**Request Body:**
```json
{
  "username": "john.doe",
  "password": "password123",
  "email": "john@company.com",
  "fullName": "John Doe",
  "role": "EMPLOYEE"
}
```

**Response:**
```json
{
  "success": true,
  "message": "User registered successfully",
  "data": {
    "id": 1,
    "username": "john.doe",
    "email": "john@company.com",
    "fullName": "John Doe",
    "role": "EMPLOYEE",
    "active": true
  }
}
```

### 1.2 Login
**Endpoint:** `POST /api/auth/login`

**Request Body:**
```json
{
  "username": "admin",
  "password": "admin123"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJzdWIiOiJhZG1pbiIsImlhdCI6MTYzMjE1...",
    "username": "admin",
    "role": "ADMIN",
    "fullName": "System Administrator"
  }
}
```

---

## 2. Department APIs

### 2.1 Create Department (ADMIN only)
**Endpoint:** `POST /api/departments`

**Headers:**
```
Authorization: Bearer <token>
```

**Request Body:**
```json
{
  "departmentCode": "IT",
  "name": "Information Technology",
  "description": "IT Department handling all tech operations",
  "active": true
}
```

### 2.2 Get All Departments
**Endpoint:** `GET /api/departments`

**Headers:**
```
Authorization: Bearer <token>
```

### 2.3 Get Department by ID
**Endpoint:** `GET /api/departments/{id}`

### 2.4 Update Department (ADMIN only)
**Endpoint:** `PUT /api/departments/{id}`

### 2.5 Delete Department (ADMIN only)
**Endpoint:** `DELETE /api/departments/{id}`

---

## 3. Employee APIs

### 3.1 Create Employee (ADMIN/MANAGER)
**Endpoint:** `POST /api/employees`

**Request Body:**
```json
{
  "user": {
    "id": 2
  },
  "employeeCode": "EMP001",
  "department": {
    "id": 1
  },
  "designation": "Software Engineer",
  "dateOfJoining": "2024-01-15",
  "dateOfBirth": "1995-05-20",
  "phoneNumber": "1234567890",
  "address": "123 Main St, City",
  "salary": 75000.00,
  "manager": {
    "id": 1
  }
}
```

### 3.2 Get All Employees
**Endpoint:** `GET /api/employees`

### 3.3 Get Employee by ID
**Endpoint:** `GET /api/employees/{id}`

### 3.4 Get Employees by Department
**Endpoint:** `GET /api/employees/department/{departmentId}`

### 3.5 Update Employee (ADMIN/MANAGER)
**Endpoint:** `PUT /api/employees/{id}`

### 3.6 Delete Employee (ADMIN only)
**Endpoint:** `DELETE /api/employees/{id}`

---

## 4. Attendance APIs

### 4.1 Mark Attendance
**Endpoint:** `POST /api/attendance`

**Request Body:**
```json
{
  "employee": {
    "id": 1
  },
  "date": "2024-12-10",
  "checkInTime": "09:00:00",
  "checkOutTime": "18:00:00",
  "status": "PRESENT",
  "remarks": "On time"
}
```

**Status Options:** `PRESENT`, `ABSENT`, `HALF_DAY`, `ON_LEAVE`, `HOLIDAY`, `WEEKEND`

### 4.2 Update Check-Out Time
**Endpoint:** `PUT /api/attendance/{id}/checkout?checkOutTime=18:30:00`

### 4.3 Get Employee Attendance
**Endpoint:** `GET /api/attendance/employee/{employeeId}`

### 4.4 Get Attendance by Date Range
**Endpoint:** `GET /api/attendance/employee/{employeeId}/range?startDate=2024-12-01&endDate=2024-12-10`

### 4.5 Get Attendance by Date
**Endpoint:** `GET /api/attendance/date/2024-12-10`

---

## 5. Leave Management APIs

### 5.1 Create Leave Request
**Endpoint:** `POST /api/leaves`

**Request Body:**
```json
{
  "employee": {
    "id": 1
  },
  "leaveType": "SICK_LEAVE",
  "startDate": "2024-12-15",
  "endDate": "2024-12-17",
  "reason": "Medical appointment and recovery"
}
```

**Leave Types:** 
- `SICK_LEAVE` (10 days)
- `CASUAL_LEAVE` (12 days)
- `ANNUAL_LEAVE` (15 days)
- `MATERNITY_LEAVE` (90 days)
- `PATERNITY_LEAVE` (7 days)
- `UNPAID_LEAVE`

### 5.2 Approve Leave Request (ADMIN/MANAGER)
**Endpoint:** `PUT /api/leaves/{id}/approve?remarks=Approved`

### 5.3 Reject Leave Request (ADMIN/MANAGER)
**Endpoint:** `PUT /api/leaves/{id}/reject?remarks=Not approved due to project deadline`

### 5.4 Get Employee Leave Requests
**Endpoint:** `GET /api/leaves/employee/{employeeId}`

### 5.5 Get Pending Leave Requests (ADMIN/MANAGER)
**Endpoint:** `GET /api/leaves/pending`

### 5.6 Get Leave Balance
**Endpoint:** `GET /api/leaves/balance/{employeeId}/{year}`

**Example:** `GET /api/leaves/balance/1/2024`

**Response:**
```json
{
  "success": true,
  "message": "Leave balance retrieved successfully",
  "data": {
    "id": 1,
    "employee": {
      "id": 1
    },
    "year": 2024,
    "sickLeaveBalance": 8,
    "casualLeaveBalance": 10,
    "annualLeaveBalance": 15,
    "maternityLeaveBalance": 90,
    "paternityLeaveBalance": 7
  }
}
```

---

## Common Response Format

### Success Response
```json
{
  "success": true,
  "message": "Operation successful",
  "data": { }
}
```

### Error Response
```json
{
  "success": false,
  "message": "Error message here",
  "data": null
}
```

---

## HTTP Status Codes

- `200 OK` - Success
- `201 Created` - Resource created successfully
- `400 Bad Request` - Invalid request data
- `401 Unauthorized` - Missing or invalid token
- `403 Forbidden` - Insufficient permissions
- `404 Not Found` - Resource not found
- `500 Internal Server Error` - Server error

---

## Role-Based Access Control

| Endpoint | ADMIN | MANAGER | EMPLOYEE |
|----------|-------|---------|----------|
| Auth APIs | ✅ | ✅ | ✅ |
| View Departments | ✅ | ✅ | ✅ |
| Manage Departments | ✅ | ❌ | ❌ |
| View Employees | ✅ | ✅ | ✅ |
| Create/Update Employees | ✅ | ✅ | ❌ |
| Delete Employees | ✅ | ❌ | ❌ |
| Attendance Operations | ✅ | ✅ | ✅ |
| Leave Requests | ✅ | ✅ | ✅ |
| Approve/Reject Leaves | ✅ | ✅ | ❌ |

---

## Testing with Postman

1. **Import Collection** - Create a new collection in Postman
2. **Set Variables:**
   - `baseUrl`: `http://localhost:8080`
   - `token`: Your JWT token from login
3. **Login First** - Use `/api/auth/login` to get token
4. **Set Authorization** - Add `Bearer {{token}}` to protected requests
5. **Test Endpoints** - Start with GET requests, then try POST/PUT/DELETE

---

## Testing with cURL

### Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'
```

### Create Department
```bash
curl -X POST http://localhost:8080/api/departments \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -d '{"departmentCode":"IT","name":"IT Department","active":true}'
```

### Get All Employees
```bash
curl -X GET http://localhost:8080/api/employees \
  -H "Authorization: Bearer YOUR_TOKEN"
```

---

## Date & Time Formats

- **Date:** `YYYY-MM-DD` (e.g., `2024-12-10`)
- **Time:** `HH:mm:ss` (e.g., `09:30:00`)
- **DateTime:** `YYYY-MM-DDTHH:mm:ss` (e.g., `2024-12-10T09:30:00`)

---

**Happy Testing! 🚀**
