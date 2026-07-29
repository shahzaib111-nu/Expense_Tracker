# 💰 Expense Tracker
 
A full-stack Expense Tracker web application designed to help users efficiently monitor their expenses, manage monthly budget targets, and track spending categories.
 
Built with a **Java Spring Boot** REST API backend and a lightweight **vanilla HTML/CSS/JavaScript** frontend, the app focuses on clean, enterprise-style architecture — secure authentication, layered service design, and a responsive transaction management UI.
 
---
 
## ✨ Features & Functionality
 
- **User Authentication** — Secure registration and login with token-based session verification (`authToken` / `jwt`).
- **Expense Management** — Record, edit, delete, and view detailed expense logs.
- **Category Filtering** — Filter expenses instantly by category (Food, Transport, Shopping, Health, Education, Entertainment, Bills, Housing, Other).
- **Monthly Budget Tracking** — Set monthly budget goals and monitor spending against them in real time.
- **Reports & Analytics** — Summarized breakdowns of spending by category, month, and trend.
- **CSV Export** — Download a complete breakdown of recorded transactions as a CSV file.
- **Protected Routes** — Automatic client-side and server-side redirection preventing unauthorized access to dashboards or transaction lists.
---
 
## 🛠️ Tech Stack
 
**Backend**
- Java 17+
- Spring Boot
- Spring Security (JWT-based authentication)
- Spring Data JPA (Hibernate)
- PostgreSQL
- Redis (caching / session support)
- Maven
**Frontend**
- HTML5, CSS3, JavaScript (Vanilla)
- Fetch API for REST communication
- Responsive, utility-inspired styling (emerald/slate theme)
**Infrastructure**
- Docker & Docker Compose
---
 
## 📋 Prerequisites
 
Before running the application, ensure you have the following installed on your system:
 
- **Java Development Kit (JDK 17 or higher)**
- **Maven** (for building the backend)
- **Docker & Docker Compose** (for containerized execution)
- **PostgreSQL** (if running the backend without Docker)
- **Visual Studio Code (VS Code)** (for running the frontend)
- **Live Server Extension** (installed inside VS Code)
---
 
## 🚀 Getting Started & Running the Application
 
Choose one of the following methods to run the backend services, followed by instructions to launch the frontend.
 
### Method 1: Running with Docker (Recommended)
 
1. Navigate to the root directory containing your `docker-compose.yaml` file.
2. Build and start the containers using Docker Compose:
```bash
   docker compose up --build
```
3. This will spin up:
   - The Spring Boot backend (default: `http://localhost:8080`)
   - The PostgreSQL database
   - The Redis cache (if configured)
4. Verify the backend is running by checking the logs:
```bash
   docker compose logs -f backend
```
5. To stop all containers:
```bash
   docker compose down
```
 
