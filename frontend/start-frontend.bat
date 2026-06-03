@echo off
echo ========================================
echo Employee Data System - Frontend Server
echo ========================================
echo.
echo Starting frontend server on port 3000...
echo.
echo Please ensure backend is running on http://localhost:8080
echo.
echo Default login credentials:
echo   Admin: admin / admin123
echo   Manager: manager / manager123
echo   Employee: employee / employee123
echo.
echo ========================================
echo.

REM Check if Python is installed
python --version >nul 2>&1
if %errorlevel% equ 0 (
    echo Starting Python HTTP server...
    echo.
    echo Frontend will be available at: http://localhost:3000
    echo Press Ctrl+C to stop the server
    echo.
    python -m http.server 3000
) else (
    echo Python not found! Please install Python or use another method to serve the frontend.
    echo.
    echo Alternative methods:
    echo 1. Install Node.js and run: npx http-server -p 3000
    echo 2. Use VS Code Live Server extension
    echo 3. Open index.html directly in browser (may have CORS issues)
    pause
)
