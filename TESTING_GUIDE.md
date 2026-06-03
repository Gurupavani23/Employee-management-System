# Testing Guide - Employee Data Handling System

Complete testing guide for both backend and frontend of the Employee Data Handling System.

---

## 📋 Table of Contents
1. [Backend Testing](#backend-testing)
2. [Frontend Testing](#frontend-testing)
3. [Integration Testing](#integration-testing)
4. [API Testing with Postman](#api-testing-with-postman)
5. [Manual Testing Checklist](#manual-testing-checklist)
6. [Common Issues & Solutions](#common-issues--solutions)

---

## 🔧 Backend Testing

### Prerequisites
- Backend running on `http://localhost:8080`
- Database (MySQL or H2) configured and accessible
- Postman or curl installed

### 1. Authentication Testing

#### Test Login - Admin
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'
```

**Expected Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR...",
  "username": "admin",
  "role": "ADMIN"
}
```

#### Test Login - Invalid Credentials
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"wrongpass"}'
```

**Expected Response:** 401 Unauthorized

---

### 2. Employee CRUD Testing

#### Get All Employees
```bash
curl -X GET http://localhost:8080/api/employees \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

**Expected Response:** Array of employee objects

#### Create Employee
```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@company.com",
    "phone": "1234567890",
    "address": "123 Main St",
    "hireDate": "2024-01-15",
    "salary": 50000,
    "department": {"id": 1}
  }'
```

**Expected Response:** Created employee object with ID

#### Update Employee
```bash
curl -X PUT http://localhost:8080/api/employees/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Smith",
    "email": "john.smith@company.com",
    "phone": "1234567890",
    "salary": 55000
  }'
```

**Expected Response:** Updated employee object

#### Delete Employee
```bash
curl -X DELETE http://localhost:8080/api/employees/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

**Expected Response:** 200 OK or 204 No Content

---

### 3. Department Testing

#### Get All Departments
```bash
curl -X GET http://localhost:8080/api/departments \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

#### Create Department
```bash
curl -X POST http://localhost:8080/api/departments \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "departmentCode": "IT",
    "name": "Information Technology",
    "description": "IT Department",
    "active": true
  }'
```

---

### 4. Attendance Testing

#### Mark Attendance
```bash
curl -X POST http://localhost:8080/api/attendance \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "employee": {"id": 1},
    "date": "2024-01-15",
    "checkInTime": "09:00",
    "checkOutTime": "17:00",
    "status": "PRESENT"
  }'
```

#### Get Attendance by Date
```bash
curl -X GET "http://localhost:8080/api/attendance/date/2024-01-15" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

### 5. Leave Management Testing

#### Apply Leave
```bash
curl -X POST http://localhost:8080/api/leaves \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "employee": {"id": 1},
    "leaveType": "SICK_LEAVE",
    "startDate": "2024-01-20",
    "endDate": "2024-01-22",
    "reason": "Medical appointment"
  }'
```

#### Get Pending Leaves
```bash
curl -X GET http://localhost:8080/api/leaves/pending \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

#### Approve Leave
```bash
curl -X PUT "http://localhost:8080/api/leaves/1/approve?remarks=Approved" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

## 🌐 Frontend Testing

### Prerequisites
- Frontend running on `http://localhost:3000`
- Backend running on `http://localhost:8080`
- Modern web browser with DevTools

### 1. Login Page Testing

#### Test Steps:
1. Open `http://localhost:3000`
2. Verify login form is displayed
3. Enter credentials: admin / admin123
4. Click "Login" button
5. Verify redirect to admin dashboard
6. Check localStorage for JWT token

**Expected Results:**
- ✅ Login form visible
- ✅ No console errors
- ✅ Successful redirect
- ✅ Token stored in localStorage

#### Test Invalid Login:
1. Enter wrong credentials
2. Click "Login"
3. Verify error message displayed
4. Verify no redirect occurs

**Expected Results:**
- ❌ Error message shown
- ❌ No token in localStorage
- ❌ Still on login page

---

### 2. Admin Dashboard Testing

#### Test Steps:
1. Login as admin
2. Verify dashboard loads
3. Check statistics cards display numbers
4. Verify sidebar navigation visible
5. Check recent employees table shows data

**Expected Results:**
- ✅ 4 statistics cards visible
- ✅ Employee count > 0
- ✅ Department count > 0
- ✅ Sidebar menu active
- ✅ No console errors

#### Check Each Statistic:
- [ ] Total Employees - Shows correct count
- [ ] Total Departments - Shows correct count
- [ ] Today's Attendance - Shows percentage
- [ ] Pending Leaves - Shows count

---

### 3. Employee Management Testing

#### Test Add Employee:
1. Navigate to Employees page
2. Click "+ Add Employee" button
3. Fill in all fields:
   - First Name: Test
   - Last Name: User
   - Email: test@company.com
   - Phone: 9876543210
   - Address: Test Address
   - Hire Date: Today's date
   - Salary: 45000
   - Department: Select one
4. Click "Save"
5. Verify success message
6. Verify new employee in table

**Expected Results:**
- ✅ Modal opens on click
- ✅ All fields editable
- ✅ Department dropdown populated
- ✅ Save successful
- ✅ New row in table
- ✅ Modal closes

#### Test Edit Employee:
1. Click "Edit" button on any employee
2. Modal opens with pre-filled data
3. Change first name
4. Click "Save"
5. Verify table updates

**Expected Results:**
- ✅ Modal shows employee data
- ✅ Can modify fields
- ✅ Save works
- ✅ Table refreshes

#### Test Delete Employee:
1. Click "Delete" button
2. Confirmation dialog appears
3. Click "OK"
4. Verify employee removed from table

**Expected Results:**
- ✅ Confirmation shown
- ✅ Delete successful
- ✅ Row removed
- ✅ Success message

#### Test Search:
1. Type employee name in search box
2. Verify table filters as you type
3. Clear search
4. Verify all employees show again

**Expected Results:**
- ✅ Real-time filtering
- ✅ Case-insensitive search
- ✅ Clear works

---

### 4. Department Management Testing

#### Test Add Department:
1. Navigate to Departments page
2. Click "+ Add Department"
3. Fill in:
   - Code: TEST
   - Name: Test Department
   - Description: Test Description
   - Active: Checked
4. Click "Save"
5. Verify new card appears

**Expected Results:**
- ✅ Modal opens
- ✅ Save successful
- ✅ New card visible
- ✅ Status badge shows "Active"

#### Test Edit Department:
1. Click "Edit" on a department card
2. Change name
3. Uncheck "Active"
4. Save
5. Verify card updates

**Expected Results:**
- ✅ Edit works
- ✅ Status changes to "Inactive"
- ✅ Badge color changes (green → red)

---

### 5. Attendance Management Testing

#### Test Mark Attendance:
1. Navigate to Attendance page
2. Click "+ Mark Attendance"
3. Select employee
4. Select today's date
5. Enter check-in: 09:00
6. Enter check-out: 17:00
7. Select status: Present
8. Save
9. Verify attendance in table

**Expected Results:**
- ✅ Employee dropdown populated
- ✅ Date picker works
- ✅ Time inputs work
- ✅ Save successful
- ✅ Table shows new record
- ✅ Working hours calculated (8.0)

#### Test Date Filter:
1. Select different date
2. Click "Filter"
3. Verify table shows that date's records
4. Select today
5. Verify today's records show

**Expected Results:**
- ✅ Filter works
- ✅ Data changes per date
- ✅ No errors

---

### 6. Leave Management Testing

#### Test Apply Leave:
1. Navigate to Leaves page
2. Click "+ Apply Leave"
3. Select employee
4. Select leave type: Sick Leave
5. Start date: Tomorrow
6. End date: 2 days later
7. Reason: Medical checkup
8. Click "Apply"
9. Verify in pending table

**Expected Results:**
- ✅ Form opens
- ✅ Date pickers work
- ✅ Leave types populated
- ✅ Save successful
- ✅ Shows in pending table
- ✅ Status: "Pending" (yellow badge)
- ✅ Days calculated correctly (3)

#### Test Approve Leave:
1. Find pending leave request
2. Click "Approve" button
3. Enter remarks
4. Click "Approve"
5. Verify status changes

**Expected Results:**
- ✅ Remarks modal opens
- ✅ Approve works
- ✅ Status changes to "Approved"
- ✅ Badge turns green
- ✅ Action buttons disappear

#### Test Reject Leave:
1. Find pending leave
2. Click "Reject"
3. Enter rejection reason
4. Click "Reject"
5. Verify status changes

**Expected Results:**
- ✅ Reject works
- ✅ Status: "Rejected"
- ✅ Badge turns red

---

## 🔗 Integration Testing

### End-to-End User Flow Test

#### Complete Employee Lifecycle:
1. **Login** as admin
2. **Create Department**
   - Navigate to Departments
   - Add "Quality Assurance" department
   - Verify created
3. **Create Employee**
   - Navigate to Employees
   - Add employee in QA department
   - Verify created
4. **Mark Attendance**
   - Navigate to Attendance
   - Mark present for new employee
   - Verify recorded
5. **Apply Leave**
   - Navigate to Leaves
   - Apply leave for employee
   - Verify pending
6. **Approve Leave**
   - Approve the leave request
   - Verify approved
7. **Verify Data Consistency**
   - Check dashboard statistics updated
   - Check employee count increased
   - Check department count increased

**Expected Results:**
- ✅ All operations complete successfully
- ✅ Data consistent across pages
- ✅ No errors in console
- ✅ Statistics update correctly

---

## 📮 API Testing with Postman

### Setup Postman Collection

1. Import `Employee-Data-System.postman_collection.json`
2. Create environment with variables:
   - `base_url`: `http://localhost:8080`
   - `token`: (will be set automatically)

### Test Sequence

#### 1. Authentication
- **Login Admin** → Save token
- **Login Manager** → Save token
- **Login Employee** → Save token

#### 2. Departments
- **Get All Departments**
- **Create Department**
- **Get Department by ID**
- **Update Department**
- **Delete Department**

#### 3. Employees
- **Get All Employees**
- **Create Employee**
- **Get Employee by ID**
- **Update Employee**
- **Get Employees by Department**
- **Delete Employee**

#### 4. Attendance
- **Mark Attendance**
- **Get Attendance by Date**
- **Get Attendance by Employee**
- **Update Checkout Time**

#### 5. Leaves
- **Create Leave Request**
- **Get Pending Leaves**
- **Get Leave by Employee**
- **Approve Leave**
- **Reject Leave**
- **Get Leave Balance**

### Run Entire Collection
1. Click "Runner" in Postman
2. Select collection
3. Click "Run"
4. Verify all tests pass

**Expected Results:**
- ✅ All requests return 200/201
- ✅ No 401/403 errors
- ✅ Response data correct
- ✅ Token authentication works

---

## ✅ Manual Testing Checklist

### Pre-Testing Setup
- [ ] Backend server running (port 8080)
- [ ] Frontend server running (port 3000)
- [ ] Database accessible and populated
- [ ] Browser DevTools open (F12)
- [ ] Network tab monitoring requests

### Authentication Tests
- [ ] Login with admin credentials
- [ ] Login with manager credentials
- [ ] Login with employee credentials
- [ ] Login with invalid credentials (should fail)
- [ ] Logout and verify token removed
- [ ] Access protected page without login (should redirect)

### Dashboard Tests
- [ ] Statistics cards show correct numbers
- [ ] Recent employees table populated
- [ ] Sidebar navigation works
- [ ] User name displayed correctly
- [ ] Logout button works

### Employee Management Tests
- [ ] View all employees
- [ ] Search employees by name
- [ ] Filter employees by department
- [ ] Add new employee
- [ ] Edit existing employee
- [ ] Delete employee (with confirmation)
- [ ] Verify validation on required fields
- [ ] Check department dropdown populated

### Department Management Tests
- [ ] View all departments as cards
- [ ] Add new department
- [ ] Edit department
- [ ] Toggle active/inactive status
- [ ] Delete department
- [ ] Verify status badge colors

### Attendance Management Tests
- [ ] View today's attendance
- [ ] Filter attendance by date
- [ ] Mark attendance for employee
- [ ] Verify working hours calculated
- [ ] Check status badges display correctly
- [ ] Add attendance remarks

### Leave Management Tests
- [ ] View pending leave requests
- [ ] Apply new leave request
- [ ] Verify leave days calculated
- [ ] Approve leave request
- [ ] Reject leave request
- [ ] Check status badge updates
- [ ] Verify remarks saved

### Cross-Page Tests
- [ ] Navigate between all pages
- [ ] Verify data consistency across pages
- [ ] Check sidebar active state updates
- [ ] Refresh page doesn't lose auth
- [ ] Back button works correctly

### Responsive Design Tests
- [ ] Test on desktop (1920px)
- [ ] Test on laptop (1366px)
- [ ] Test on tablet (768px)
- [ ] Test on mobile (375px)
- [ ] Verify sidebar collapses on mobile
- [ ] Check modals fit on small screens

### Browser Compatibility Tests
- [ ] Chrome
- [ ] Firefox
- [ ] Edge
- [ ] Safari (if available)

---

## 🐛 Common Issues & Solutions

### Issue 1: CORS Error
**Symptom:** Console shows CORS policy error

**Solution:**
1. Check backend SecurityConfig has CORS enabled
2. Verify frontend URL matches allowed origin
3. Restart backend after config change

```java
cors.setAllowedOrigins(List.of("http://localhost:3000"));
```

### Issue 2: 401 Unauthorized
**Symptom:** All API calls return 401

**Solution:**
1. Check JWT token in localStorage
2. Verify token not expired (10 hours)
3. Re-login to get fresh token
4. Check Authorization header format: `Bearer YOUR_TOKEN`

### Issue 3: Loading Forever
**Symptom:** Pages stuck on "Loading..."

**Solution:**
1. Open browser console (F12)
2. Check for JavaScript errors
3. Verify API endpoints in config.js
4. Check Network tab for failed requests
5. Verify backend is running

### Issue 4: Empty Dropdowns
**Symptom:** Department/Employee dropdowns empty

**Solution:**
1. Check if departments/employees exist in database
2. Verify API endpoints return data
3. Check console for errors
4. Create test data if database empty

### Issue 5: Form Validation Fails
**Symptom:** Can't submit forms

**Solution:**
1. Check all required fields filled
2. Verify date format (YYYY-MM-DD)
3. Check email format valid
4. Check number fields have numbers

### Issue 6: Token Expired
**Symptom:** Suddenly getting 401 errors

**Solution:**
1. JWT tokens expire after 10 hours
2. Logout and login again
3. Consider implementing token refresh
4. Check token expiration in backend

### Issue 7: Database Connection Failed
**Symptom:** Backend won't start, connection errors

**Solution:**
1. Check MySQL running: `systemctl status mysql`
2. Verify database exists
3. Check username/password in application.properties
4. Or switch to H2 database for testing

---

## 📊 Testing Metrics

### Coverage Goals
- **Backend:** 80%+ unit test coverage
- **API Endpoints:** 100% tested
- **Frontend:** All CRUD operations tested
- **Integration:** All user flows tested

### Performance Benchmarks
- **API Response Time:** < 200ms
- **Page Load Time:** < 2 seconds
- **Database Query Time:** < 100ms
- **Login Time:** < 1 second

---

## 🔄 Continuous Testing

### Daily Testing
- [ ] Run Postman collection
- [ ] Check all pages load
- [ ] Verify login works
- [ ] Test one CRUD operation per module

### Weekly Testing
- [ ] Full manual testing checklist
- [ ] Test all user roles
- [ ] Check responsive design
- [ ] Review error logs

### Pre-Deployment Testing
- [ ] Complete integration testing
- [ ] Performance testing
- [ ] Security testing
- [ ] Browser compatibility testing
- [ ] Mobile testing

---

## 📝 Bug Reporting Template

When you find a bug, report it with:

```
**Title:** Short description

**Steps to Reproduce:**
1. Navigate to...
2. Click on...
3. Enter...

**Expected Behavior:**
Should show...

**Actual Behavior:**
Shows error...

**Screenshots:**
[Attach if applicable]

**Environment:**
- Browser: Chrome 121
- OS: Windows 11
- Backend Version: 1.0.0
- Frontend Version: 1.0.0

**Console Errors:**
[Copy any errors from browser console]

**Additional Context:**
Any other relevant information...
```

---

## 🎯 Testing Best Practices

1. **Always test in order:** Auth → CRUD → Integration
2. **Check console:** No errors should appear
3. **Verify data:** Check database for changes
4. **Test negative cases:** Invalid inputs, missing fields
5. **Test edge cases:** Empty lists, large numbers, special characters
6. **Clear cache:** If things look wrong
7. **Use fresh data:** For consistent results
8. **Document issues:** Keep track of bugs found
9. **Retest fixes:** Verify bugs are resolved
10. **Automate when possible:** Use Postman collections

---

This testing guide ensures comprehensive coverage of all system functionality! 🧪✅
