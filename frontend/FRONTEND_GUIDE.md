# Employee Data Handling System - Frontend Guide

## Overview
This is the complete web frontend for the Employee Data Handling System. It provides a responsive, user-friendly interface to interact with the Java Spring Boot backend.

## Technology Stack
- **HTML5** - Semantic markup
- **CSS3** - Modern styling with CSS Grid, Flexbox, and custom properties
- **Vanilla JavaScript (ES6+)** - No frameworks, just pure JavaScript
- **Fetch API** - For HTTP requests to the backend
- **LocalStorage** - For JWT token persistence

## Project Structure
```
frontend/
├── index.html              # Login page (entry point)
├── css/
│   └── style.css          # Complete styling system
├── js/
│   ├── config.js          # API configuration
│   ├── api.js             # API service layer
│   ├── auth.js            # Authentication logic
│   ├── admin-dashboard.js # Dashboard functionality
│   ├── employees.js       # Employee management
│   ├── departments.js     # Department management
│   ├── attendance.js      # Attendance tracking
│   └── leaves.js          # Leave management
├── pages/
│   ├── admin-dashboard.html
│   ├── employees.html
│   ├── departments.html
│   ├── attendance.html
│   └── leaves.html
└── assets/
    └── images/            # Logo and images
```

## Features

### 1. Authentication
- **Login Page** (`index.html`)
  - Username and password authentication
  - JWT token storage in localStorage
  - Password visibility toggle
  - "Remember Me" functionality
  - Automatic redirection based on user role

### 2. Admin Dashboard (`admin-dashboard.html`)
- Statistics cards showing:
  - Total employees
  - Total departments
  - Today's attendance rate
  - Pending leave requests
- Recent employees table
- Quick navigation sidebar

### 3. Employee Management (`employees.html`)
- View all employees in a data table
- Add new employees via modal form
- Edit existing employee details
- Delete employees with confirmation
- Search and filter functionality
- Fields: First Name, Last Name, Email, Phone, Address, Hire Date, Salary, Department, Manager

### 4. Department Management (`departments.html`)
- Card-based view of departments
- Add/Edit/Delete departments
- Fields: Department Code, Name, Description, Active status
- Visual status indicators (Active/Inactive badges)

### 5. Attendance Management (`attendance.html`)
- Mark daily attendance for employees
- View attendance by date
- Date filter to view historical records
- Fields: Employee, Date, Check-In Time, Check-Out Time, Working Hours, Status, Remarks
- Status types: Present, Absent, Half Day, On Leave, Holiday, Weekend

### 6. Leave Management (`leaves.html`)
- Apply for leave requests
- View all pending leave requests
- Approve/Reject leave requests with remarks
- Leave types: Sick Leave, Casual Leave, Annual Leave, Maternity Leave, Paternity Leave, Unpaid Leave
- Status badges: Pending, Approved, Rejected, Cancelled

## Setup Instructions

### Prerequisites
1. Backend server running on `http://localhost:8080`
2. Modern web browser (Chrome, Firefox, Edge)
3. A simple HTTP server to serve the frontend files

### Method 1: Using Python HTTP Server (Recommended)
```bash
cd frontend
python -m http.server 3000
```
Then open: http://localhost:3000

### Method 2: Using Node.js http-server
```bash
npm install -g http-server
cd frontend
http-server -p 3000
```

### Method 3: Using Live Server VS Code Extension
1. Install "Live Server" extension in VS Code
2. Right-click on `index.html`
3. Select "Open with Live Server"

### Method 4: Direct File Access (Not Recommended)
You can open `index.html` directly in your browser, but CORS may block API requests.

## Default Login Credentials

After starting the backend, use these credentials:

### Admin Account
- **Username:** admin
- **Password:** admin123
- **Access:** Full system access

### Manager Account
- **Username:** manager
- **Password:** manager123
- **Access:** Team management features

### Employee Account
- **Username:** employee
- **Password:** employee123
- **Access:** Self-service features

## API Configuration

The frontend communicates with the backend via REST APIs. Configuration is in `js/config.js`:

```javascript
const API_CONFIG = {
    BASE_URL: 'http://localhost:8080/api',
    ENDPOINTS: {
        LOGIN: '/auth/login',
        EMPLOYEES: '/employees',
        DEPARTMENTS: '/departments',
        ATTENDANCE: '/attendance',
        LEAVES: '/leaves'
    }
};
```

If your backend runs on a different port or domain, update the `BASE_URL`.

## Architecture

### API Service Layer (`js/api.js`)
Centralized API communication with:
- **Token Management** - Automatic JWT token inclusion in requests
- **Error Handling** - Unified error handling across all API calls
- **Request/Response** - Generic methods for GET, POST, PUT, DELETE

### Authentication Flow
1. User enters credentials on login page
2. Frontend sends POST request to `/api/auth/login`
3. Backend validates and returns JWT token
4. Token stored in localStorage
5. Token included in all subsequent API requests via Authorization header
6. User redirected to appropriate dashboard based on role

### Data Flow Example (Employee CRUD)
```
User Action → JavaScript Event → API Service → Backend REST API
                                      ↓
User Interface ← DOM Update ← Response Handler ← JSON Response
```

## Styling System

### CSS Custom Properties (Variables)
```css
--primary-color: #2c3e50
--success-color: #27ae60
--danger-color: #e74c3c
--warning-color: #f39c12
--info-color: #3498db
```

### Responsive Design
- Mobile-first approach
- Breakpoints: 768px (tablet), 1024px (desktop)
- Flexible grid system
- Collapsible sidebar navigation on mobile

### Component Classes
- `.card` - Content containers
- `.btn` - Buttons with variants (primary, secondary, success, danger)
- `.modal` - Dialog windows for forms
- `.table-container` - Responsive tables
- `.badge` - Status indicators
- `.form-group` - Form field groups

## Browser Compatibility
- Chrome 90+
- Firefox 88+
- Safari 14+
- Edge 90+

## Security Features

### JWT Authentication
- Tokens stored in localStorage
- Automatic token expiration handling
- Token included in Authorization header for protected routes

### Input Validation
- Required field validation
- HTML5 form validation
- Client-side data sanitization

### CORS Configuration
Backend must have CORS enabled for frontend origin:
```java
@CrossOrigin(origins = "http://localhost:3000")
```

## Troubleshooting

### Issue: API calls failing with CORS error
**Solution:** Ensure backend SecurityConfig has CORS configuration:
```java
http.cors().configurationSource(request -> {
    var cors = new CorsConfiguration();
    cors.setAllowedOrigins(List.of("http://localhost:3000"));
    cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
    cors.setAllowedHeaders(List.of("*"));
    return cors;
});
```

### Issue: Login returns 401 Unauthorized
**Solution:** 
1. Check if backend is running
2. Verify default users are initialized (check DataInitializer.java)
3. Check username/password match default credentials

### Issue: Pages show "Loading..." indefinitely
**Solution:**
1. Open browser console (F12)
2. Check for JavaScript errors
3. Verify API endpoints in config.js match backend
4. Ensure backend is accessible at configured BASE_URL

### Issue: Token expired errors
**Solution:**
- JWT tokens expire after 10 hours
- Log out and log back in to get a new token
- Consider implementing token refresh logic for production

## Development Tips

### Adding New Features
1. Create HTML page in `pages/` directory
2. Create corresponding JavaScript file in `js/` directory
3. Add new API methods to `api.js` if needed
4. Update sidebar navigation in all pages
5. Add endpoint configuration in `config.js`

### Debugging
- Open browser DevTools (F12)
- Check Console tab for JavaScript errors
- Check Network tab for API request/response details
- Verify localStorage contains valid token

### Testing
1. Test all CRUD operations for each module
2. Verify form validations work correctly
3. Test with different user roles
4. Check responsive design on different screen sizes
5. Verify error handling displays user-friendly messages

## Performance Optimization

### Current Implementation
- Vanilla JavaScript (no framework overhead)
- Minimal external dependencies
- Efficient DOM manipulation
- CSS-only animations

### Future Improvements
- Implement pagination for large data sets
- Add debouncing for search inputs
- Lazy load images
- Cache frequently accessed data
- Implement service workers for offline support

## Next Steps

### Recommended Enhancements
1. **Form Validation** - Add comprehensive client-side validation
2. **Loading States** - Add loading spinners during API calls
3. **Toast Notifications** - Better user feedback for actions
4. **Data Export** - Add CSV/PDF export for reports
5. **Charts & Graphs** - Visualize attendance and leave statistics
6. **Dark Mode** - Theme toggle for user preference
7. **Internationalization** - Multi-language support
8. **Accessibility** - ARIA labels, keyboard navigation

## Support
For issues or questions:
1. Check the main README.md
2. Review API_DOCUMENTATION.md for backend endpoints
3. Check browser console for error messages
4. Verify backend is running and accessible

## License
This frontend is part of the Employee Data Handling System project.
