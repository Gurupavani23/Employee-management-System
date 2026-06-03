# 🚀 Quick Start Guide - Getting Your System Running

## The Problem
You're seeing "Failed to fetch" because the **backend server isn't running yet**.

The frontend (HTML/CSS/JavaScript) is trying to connect to the backend (Java Spring Boot) at `http://localhost:8080`, but the backend hasn't been started.

---

## ✅ Solution - Start the Backend (Choose ONE Method)

### Method 1: Using IntelliJ IDEA (RECOMMENDED - EASIEST)

1. **Open IntelliJ IDEA**
2. Click **File → Open**
3. Navigate to: `d:\Mtech\Project Java\employee-data-system`
4. Select the folder and click **OK**
5. Wait for IntelliJ to import the Maven project (bottom right corner shows progress)
6. Once imported, find: `src/main/java/com/employee/system/EmployeeDataSystemApplication.java`
7. Right-click on the file → **Run 'EmployeeDataSystemApplication'**
8. Wait for console to show: `Started EmployeeDataSystemApplication in X seconds`
9. **Backend is now running on port 8080!** ✅

### Method 2: Using VS Code with Java Extensions

1. **Install Extensions** (if not already installed):
   - Extension Pack for Java (by Microsoft)
   - Spring Boot Extension Pack

2. **Open Project**:
   - Open VS Code
   - File → Open Folder
   - Select: `d:\Mtech\Project Java\employee-data-system`

3. **Run Application**:
   - Open: `src/main/java/com/employee/system/EmployeeDataSystemApplication.java`
   - Click **Run** button above the `main` method
   - Or press **F5**

### Method 3: Install Maven and Run from Terminal

1. **Download Maven**:
   - Go to: https://maven.apache.org/download.cgi
   - Download `apache-maven-3.9.x-bin.zip`
   - Extract to: `C:\Program Files\Apache\Maven`

2. **Add to PATH**:
   - Search "Environment Variables" in Windows
   - Edit System Environment Variables
   - Add to PATH: `C:\Program Files\Apache\Maven\bin`
   - Click OK

3. **Verify Installation**:
   ```powershell
   mvn -version
   ```

4. **Build and Run**:
   ```powershell
   cd "d:\Mtech\Project Java\employee-data-system"
   mvn clean install
   mvn spring-boot:run
   ```

---

## 📱 Start the Frontend

Once backend is running, open a **NEW PowerShell terminal**:

```powershell
cd "d:\Mtech\Project Java\employee-data-system\frontend"
python -m http.server 3000
```

Then open your browser: **http://localhost:3000**

---

## 🔑 Now You Can Login!

### Option 1: Use Test Accounts (Already Created)
- **Admin**: admin / admin123
- **Manager**: manager / manager123
- **Employee**: employee / employee123

### Option 2: Create New Account
1. Click "Create Account" link on login page
2. Fill in the registration form:
   - Username (min 3 characters)
   - Email
   - Password (min 6 characters)
   - Confirm Password
   - Select Role (Employee/Manager/Admin)
3. Click "Create Account"
4. You'll be redirected to login
5. Login with your new credentials!

---

## ✅ Verification Checklist

### Backend Running?
- [ ] Terminal shows "Started EmployeeDataSystemApplication"
- [ ] No red errors in console
- [ ] Port 8080 is being used

Check with:
```powershell
netstat -ano | Select-String "8080" | Select-String "LISTENING"
```

### Frontend Running?
- [ ] Python server started on port 3000
- [ ] Browser opens http://localhost:3000
- [ ] Login page visible

### Login Working?
- [ ] Click Login with admin/admin123
- [ ] No "Failed to fetch" error
- [ ] Redirects to dashboard

---

## 🐛 Troubleshooting

### "Failed to fetch" Error
**Cause**: Backend not running
**Fix**: Start backend using one of the methods above

### "Port 8080 already in use"
**Cause**: Another Java app running
**Fix**: 
```powershell
# Find the process
netstat -ano | findstr :8080
# Kill it (replace PID with actual process ID)
taskkill /PID <PID> /F
```

### "Maven not found"
**Cause**: Maven not installed
**Fix**: Use IntelliJ IDEA (Method 1) - it has built-in Maven

### Backend Starts but Login Still Fails
**Check**:
1. Backend console - any errors?
2. Database connected? (Check application.properties)
3. CORS enabled? (Should be by default)
4. Browser console (F12) - what's the exact error?

---

## 📊 Architecture Overview

```
┌─────────────────────────────────────────────────┐
│  FRONTEND (Port 3000)                           │
│  http://localhost:3000                          │
│  - Login Page (index.html)                      │
│  - Registration Page (register.html)            │
│  - Dashboard, Employees, Attendance, Leaves     │
└────────────────┬────────────────────────────────┘
                 │
                 │ HTTP Requests (Fetch API)
                 ▼
┌─────────────────────────────────────────────────┐
│  BACKEND (Port 8080)                            │
│  http://localhost:8080/api                      │
│  Spring Boot + JWT + MySQL/H2                   │
│  - /api/auth/login                              │
│  - /api/auth/register                           │
│  - /api/employees                               │
│  - /api/departments                             │
│  - /api/attendance                              │
│  - /api/leaves                                  │
└────────────────┬────────────────────────────────┘
                 │
                 │ JDBC
                 ▼
┌─────────────────────────────────────────────────┐
│  DATABASE (Port 3306)                           │
│  MySQL or H2 (in-memory)                        │
│  Tables: users, employees, departments, etc.    │
└─────────────────────────────────────────────────┘
```

---

## 🎯 Quick Win - Use IntelliJ IDEA

**This is the fastest way:**

1. Open IntelliJ IDEA
2. Open project folder
3. Wait for sync (1-2 minutes)
4. Right-click `EmployeeDataSystemApplication.java`
5. Click **Run**
6. Done! ✅

IntelliJ handles everything:
- ✅ Maven installation
- ✅ Dependency download
- ✅ Database setup (H2 in-memory)
- ✅ Running the server

---

## 📞 Still Having Issues?

1. **Check Backend Console** - Look for errors
2. **Check Browser Console** (F12) - See network errors
3. **Verify Ports**:
   - Backend: 8080 (must be free)
   - Frontend: 3000 (must be free)
4. **Database**: Use H2 (no setup needed) instead of MySQL

---

## 🎉 Success!

Once both servers are running:
- Frontend: http://localhost:3000
- Backend: http://localhost:8080
- Login works ✅
- Registration works ✅
- All features accessible ✅

**Happy coding!** 🚀
