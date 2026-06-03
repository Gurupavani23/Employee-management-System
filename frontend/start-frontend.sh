#!/bin/bash

echo "========================================"
echo "Employee Data System - Frontend Server"
echo "========================================"
echo ""
echo "Starting frontend server on port 3000..."
echo ""
echo "Please ensure backend is running on http://localhost:8080"
echo ""
echo "Default login credentials:"
echo "  Admin: admin / admin123"
echo "  Manager: manager / manager123"
echo "  Employee: employee / employee123"
echo ""
echo "========================================"
echo ""

# Check if Python 3 is installed
if command -v python3 &> /dev/null; then
    echo "Starting Python HTTP server..."
    echo ""
    echo "Frontend will be available at: http://localhost:3000"
    echo "Press Ctrl+C to stop the server"
    echo ""
    python3 -m http.server 3000
elif command -v python &> /dev/null; then
    echo "Starting Python HTTP server..."
    echo ""
    echo "Frontend will be available at: http://localhost:3000"
    echo "Press Ctrl+C to stop the server"
    echo ""
    python -m http.server 3000
else
    echo "Python not found! Please install Python or use another method to serve the frontend."
    echo ""
    echo "Alternative methods:"
    echo "1. Install Node.js and run: npx http-server -p 3000"
    echo "2. Use VS Code Live Server extension"
    echo "3. Install PHP and run: php -S localhost:3000"
    read -p "Press Enter to exit..."
fi
