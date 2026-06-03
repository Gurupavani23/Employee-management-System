# Quick Start Guide

## Option 1: Using Maven (If Maven is installed)

```bash
mvn clean install
mvn spring-boot:run
```

## Option 2: Using IDE (Recommended)

1. Open the project in **IntelliJ IDEA** or **Eclipse**
2. Wait for Maven dependencies to download
3. Run `EmployeeDataSystemApplication.java`

## Option 3: Manual Build with Java

### Step 1: Download Maven
- Download from: https://maven.apache.org/download.cgi
- Extract to `C:\apache-maven-3.9.5`
- Add to PATH: `C:\apache-maven-3.9.5\bin`

### Step 2: Build Project
```bash
cd "d:\Mtech\Project Java\employee-data-system"
mvn clean package
```

### Step 3: Run Application
```bash
java -jar target\employee-data-system-1.0.0.jar
```

## Database Setup

### Option 1: MySQL (Recommended)
```sql
CREATE DATABASE employee_db;
```

### Option 2: H2 In-Memory Database (For Testing)
Update `pom.xml` - add H2 dependency:
```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

Update `application.properties`:
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

## Access Application

Once running:
- API Base URL: http://localhost:8080
- Test login: `admin` / `admin123`

## Next Steps

1. Read `README.md` for full documentation
2. Check `API_DOCUMENTATION.md` for API details
3. Import Postman collection to test APIs
4. Start with `/api/auth/login` endpoint

## Troubleshooting

**Issue: Port 8080 already in use**
- Change port in `application.properties`: `server.port=8081`

**Issue: MySQL connection failed**
- Start MySQL service
- Verify credentials in `application.properties`

**Issue: Maven not found**
- Use IDE (IntelliJ/Eclipse) instead
- Or download and install Maven manually
