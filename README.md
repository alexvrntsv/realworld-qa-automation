# RealWorld QA Automation

Automated testing suite for the RealWorld Conduit application, including UI tests with Selenium and API tests with REST Assured.

## Tech Stack

- **Backend**: Java 21, Spring Boot 3.2.5, MySQL 8.0
- **Frontend**: Modern web UI with Docker support
- **Testing**: 
  - Selenium 4.21.0 (UI automation)
  - REST Assured 5.5.6 (API testing)
  - JUnit 5 (test framework)
  - Allure (test reporting)
- **CI/CD**: GitHub Actions with Docker

## Quick Start

### Prerequisites
- Docker & Docker Compose

### Local Setup

```bash
# Clone the repository
git clone https://github.com/alexvrntsv/realworld-qa-automation.git
cd realworld-qa-automation

# Start the application stack
docker-compose -f docker-compose-local.yml up

# Application will be available at:
# Frontend: http://localhost:3000
# API: http://localhost:8080
# Database: localhost:3308
```

### Run Tests

```bash
# Run UI tests (Selenium)
cd ui-tests
mvn clean test -Dheadless=true

# View Allure report
mvn allure:serve
```

## Project Structure

```
.
├── backend/              # Spring Boot API (Java 21)
│   ├── src/             # Source code
│   ├── pom.xml          # Maven configuration
│   └── Dockerfile       # Container image
├── frontend/            # Web UI
├── ui-tests/            # Selenium test suite (Java)
│   └── pom.xml          # Test dependencies
├── .github/workflows/   # CI/CD pipelines
└── docker-compose-*.yml # Docker configurations
```

## CI/CD Pipeline

Automated tests run on:
- Push to `main` branch
- Pull requests to `main`
- Manual trigger via GitHub Actions

**Report**: Test results are published to [GitHub Pages](https://pages.github.com/) as an Allure report.

## License

MIT License
