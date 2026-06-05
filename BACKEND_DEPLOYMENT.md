# Backend Deployment Guide

This Spring Boot backend can be deployed independently from the frontend.

## 1. Build the backend Docker image

From the project root:

```bash
docker build -t employee-data-system-backend .
```

## 2. Run locally for verification

```bash
docker run -p 8080:8080 \
  -e PORT=8080 \
  -e JWT_SECRET=your_super_secret_key \
  employee-data-system-backend
```

After startup, the backend will be available at:

- `http://localhost:8080`
- H2 console: `http://localhost:8080/h2-console`

## 3. Deploy to a cloud provider

### Recommended options

- Railway
- Render
- Fly.io
- Heroku
- AWS Elastic Beanstalk

### General deployment steps

1. Push your code to a GitHub repository.
2. Create a new app on the provider.
3. Connect the repo.
4. Set build settings to use Docker (if supported) or Maven.
5. Configure environment variables:
   - `PORT` (optional; default `8080`)
   - `JWT_SECRET` (required for production)
   - `JDBC_DATABASE_URL` (optional; defaults to H2)
   - `JDBC_DATABASE_USERNAME`
   - `JDBC_DATABASE_PASSWORD`
   - `JDBC_DATABASE_DRIVER`
   - `HIBERNATE_DIALECT`

### Example for PostgreSQL

```text
JDBC_DATABASE_URL=jdbc:postgresql://<host>:5432/<database>
JDBC_DATABASE_USERNAME=<user>
JDBC_DATABASE_PASSWORD=<password>
JDBC_DATABASE_DRIVER=org.postgresql.Driver
HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQLDialect
```

## 4. Connect frontend to the backend

1. Deploy the backend and copy its public URL.
2. Open `frontend/config.json`.
3. Set `API_BASE` to the backend URL.

Example:

```json
{
  "API_BASE": "https://your-backend.example.com"
}
```

4. Re-deploy the frontend on Vercel.

## 5. Notes

- The frontend uses `frontend/config.json` at runtime to load the backend URL.
- The backend is configured to use environment variables for port, database, and JWT secret.
- If you deploy the backend with H2 in production, the database is ephemeral and will reset on each restart. Use a managed database for persistent data.
