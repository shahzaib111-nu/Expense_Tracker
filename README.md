<div align="center">

# 💰 Expense Tracker

**A full-stack expense management app to track spending, set budgets, and analyze financial habits.**

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=flat-square&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=docker&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-Vanilla-F7DF1E?style=flat-square&logo=javascript&logoColor=black)

</div>

A full-stack Expense Tracker web application designed to help users efficiently monitor their expenses, manage monthly budget targets, and track spending categories.

Built with a **Java Spring Boot REST API** backend and a lightweight **vanilla HTML/CSS/JavaScript** frontend, the app focuses on clean, enterprise-style architecture incorporating secure authentication, layered service design, and a responsive transaction management UI.

## 📑 Table of Contents

- [Features](#-features)
- [Tech Stack](#️-tech-stack)
- [Prerequisites](#-prerequisites)
- [Getting Started](#-getting-started)
   - [Method 1: Docker](#method-1-running-with-docker-recommended)
   - [Method 2: Manual](#method-2-running-manually-without-docker)
- [Launching the Frontend](#-launching-the-frontend)

## ✨ Features

| | |
|---|---|
| 🔐 **User Authentication** | Secure registration and login with token-based session verification using JSON Web Tokens |
| 💸 **Expense Management** | Seamlessly record, edit, delete, and view detailed expense logs |
| 🗂️ **Category Filtering** | Instantly filter expenses across categories including Food, Transport, Shopping, Health, Education, Entertainment, Bills, Housing, and Other |
| 🎯 **Monthly Budget Tracking** | Set customized monthly budget goals and monitor spending thresholds in real time |
| 📊 **Reports & Analytics** | Clear, summarized breakdowns of spending by category, month, and overall trends |
| 📁 **CSV Export** | Download a comprehensive historical breakdown of transactions as a CSV file |
| 🛡️ **Protected Routes** | Automated client-side and server-side route guards preventing unauthorized dashboard access |

## 🛠️ Tech Stack

<table>
<tr><td valign="top" width="33%">

**Backend**
- Java 17+
- Spring Boot
- Spring Security (JWT)
- Spring Data JPA + Hibernate
- PostgreSQL
- Apache Maven

</td><td valign="top" width="33%">

**Frontend**
- HTML5, CSS3
- Modern Vanilla JavaScript
- Browser Fetch API
- Emerald & slate color palette

</td><td valign="top" width="33%">

**Infrastructure**
- Docker
- Docker Compose

</td></tr>
</table>

## 📋 Prerequisites

Make sure your development environment includes:

- ✅ Java Development Kit 17+
- ✅ Apache Maven
- ✅ Docker & Docker Compose
- ✅ PostgreSQL (only if running the backend natively without containers)
- ✅ VS Code (or your preferred editor) with the **Live Server** extension for frontend development

## 🚀 Getting Started

Choose between the containerized Docker setup or manual local execution for the backend, then launch the frontend.

### Method 1: Running with Docker (Recommended)

**1.** Navigate to your project root containing `docker-compose.yaml`:

```bash
cd expense-tracker
```

**2.** Build and start all services:

```bash
docker compose up --build
```

This spins up:
- 🟢 Spring Boot backend API → `port 8080`
- 🟢 PostgreSQL database instance

**3.** Verify the backend booted successfully:

```bash
docker compose logs -f backend
```

**4.** To shut everything down and clean up:

```bash
docker compose down
```

### Method 2: Running Manually (Without Docker)

**1. Database Setup**
Start your local PostgreSQL server, create a new database for the project, and point `application.properties` / `application.yml` to your local credentials.

**2. Build the Backend**

```bash
cd backend
mvn clean install
```

**3. Run the Backend**

```bash
mvn spring-boot:run
```

> The backend server will launch on **http://localhost:8080**

## 🌐 Launching the Frontend

Regardless of which backend method you chose:

1. Locate the `frontend` directory within the project structure.
2. Open the `frontend` folder in VS Code.
3. Right-click `index.html` → **Open with Live Server** (or use your preferred static file server).
4. Open your browser and navigate to:

   ```
   http://localhost:5500
   ```

   or

   ```
   http://127.0.0.1:5500
   ```

<div align="center">

**Built with Java Spring Boot, PostgreSQL, and Vanilla JS** 🚀

</div>