# Jira Flash Summary & PDF Generator

Jira Flash Summary is an Atlassian Connect application designed for Jira Cloud. It enables project managers and stakeholders to transform Jira issues, sprints, and tasks into professional PDF reports and technical briefs with a single click.

The application is built using Java 21 and Spring Boot 3, leveraging the Atlassian Connect Spring Boot Starter for secure integration with Jira Cloud.

## Technical Architecture
* Backend: Java 21 / Spring Boot 3.2.2
* Security: JWT-based authentication (Atlassian Connect Security)
* Persistence: PostgreSQL
* Containerization: Docker (Multi-stage builds)
* Deployment: Managed Cloud Environment (Render)

## Branching and Deployment Strategy
The project follows a streamlined CI/CD workflow:
* **master**: The primary branch for development. All new features and stable code should be merged here first for testing.
* **main**: The production branch. Any code merged into this branch triggers an automatic deployment to the production environment.

## Local Development Setup

### 1. Database Initialization
The application requires a PostgreSQL instance. You can initialize a local instance using Docker:
```bash
docker run --name jira-flash-db -e POSTGRES_PASSWORD=password -e POSTGRES_DB=flash_notes -p 5432:5432 -d postgres:16-alpine