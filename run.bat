@echo off
echo ============================================
echo Employee Data Handling System
echo ============================================
echo.

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java 21 or higher
    pause
    exit /b 1
)

echo Java found!
echo.

REM Check if target directory exists (project already built)
if exist "target\employee-data-system-1.0.0.jar" (
    echo Running application...
    java -jar target\employee-data-system-1.0.0.jar
) else (
    echo.
    echo NOTE: Project not built yet.
    echo.
    echo Please build the project first using one of these methods:
    echo.
    echo Option 1: Using Maven
    echo   mvn clean package
    echo.
    echo Option 2: Using IDE
    echo   - Open project in IntelliJ IDEA or Eclipse
    echo   - Wait for dependencies to download
    echo   - Run EmployeeDataSystemApplication.java
    echo.
    echo Option 3: Without Maven
    echo   - See QUICK_START.md for detailed instructions
    echo.
    pause
)
