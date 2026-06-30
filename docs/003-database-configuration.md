# 003 - Database Configuration

## Issue
The application failed to start due to missing DataSource configuration.

## Cause
Spring Boot detected JPA and PostgreSQL dependencies but no database connection settings were provided.

## Solution
A PostgreSQL database was configured in application.properties with:

- JDBC URL
- Username=guille
- Password=guille
- Hibernate dialect

## Database
- Name: crud_tienda
- Engine: PostgreSQL 16

## Reasoning
Spring Boot requires explicit DataSource configuration when JPA and database drivers are included.
