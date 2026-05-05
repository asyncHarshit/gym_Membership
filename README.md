<div align="center">

```
███████╗██╗████████╗███████╗ ██████╗ ██████╗  ██████╗███████╗
██╔════╝██║╚══██╔══╝██╔════╝██╔═══██╗██╔══██╗██╔════╝██╔════╝
█████╗  ██║   ██║   █████╗  ██║   ██║██████╔╝██║     █████╗  
██╔══╝  ██║   ██║   ██╔══╝  ██║   ██║██╔══██╗██║     ██╔══╝  
██║     ██║   ██║   ██║     ╚██████╔╝██║  ██║╚██████╗███████╗
╚═╝     ╚═╝   ╚═╝   ╚═╝      ╚═════╝ ╚═╝  ╚═╝ ╚═════╝╚══════╝
```

# 💪 FitForce — Gym Management & Workout Tracking System

**A unified web platform for gym owners, trainers, and members.**  
*Streamline operations. Empower athletes. Track every rep.*

---

[![Java](https://img.shields.io/badge/Backend-Java%20Spring%20Boot-brightgreen?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/Frontend-React.js-61DAFB?style=for-the-badge&logo=react&logoColor=black)](https://reactjs.org/)
[![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![MongoDB](https://img.shields.io/badge/NoSQL-MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white)](https://www.mongodb.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)](LICENSE)

</div>

---

## 📖 Table of Contents

- [Overview](#-overview)
- [Key Features](#-key-features)
- [User Roles](#-user-roles)
- [System Architecture](#-system-architecture)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
- [API Documentation](#-api-documentation)
- [Database Schema](#-database-schema)
- [Screenshots](#-screenshots)
- [Contributing](#-contributing)

---

## 🏋️ Overview

**FitForce** is a full-stack web application designed to bridge the gap between gym administration and member fitness journeys. Built with Java Spring Boot and React.js, it provides a seamless experience across three distinct user roles — all from a single, integrated platform.

> *No disconnected spreadsheets. No forgotten payments. No lost progress logs.*

Gym owners get powerful operational dashboards. Trainers get digital tools to coach clients remotely. Members get personalized tracking that motivates them to keep showing up.

---

## ✨ Key Features

### 🧑‍💼 For Gym Admins
| Feature | Description |
|---|---|
| 📊 **Live Dashboard** | Real-time view of active members, daily revenue, and expiring memberships |
| 💳 **Membership Engine** | Automate plan start/end dates, renewals, and membership freezing |
| 👥 **Staff Management** | Manage trainer profiles, assign shifts, and monitor availability |
| 🔔 **Smart Notifications** | Automated email/SMS reminders for fee dues and renewal alerts |
| 🔧 **Equipment Tracker** | Log equipment status, flag maintenance needs, and schedule repairs |

### 🏃 For Members
| Feature | Description |
|---|---|
| 📱 **QR Check-In** | Scan a unique QR code at gym entry — attendance logged instantly |
| 🗓️ **Class Booking** | Book slots for Yoga, Zumba, CrossFit, or personal training sessions |
| 🏋️ **Workout Logger** | Log exercises, sets, reps, and weights per session |
| 📈 **Progress Charts** | Visualize weight loss, strength gains, and body metrics over time |
| 💊 **Diet Plan Viewer** | Access custom nutrition plans assigned by your trainer |

### 🎽 For Trainers
| Feature | Description |
|---|---|
| 📋 **Client Manager** | View and manage your assigned clients in one place |
| 🧩 **Plan Builder** | Create custom workout and diet plans with a drag-and-drop interface |
| 📉 **Performance Review** | Monitor client workout logs and suggest adjustments |
| 📅 **Schedule Manager** | Set availability for personal training slots |

---

## 👥 User Roles

```
┌─────────────────────────────────────────────────────────────────┐
│                         FITFORCE PLATFORM                        │
├──────────────────┬──────────────────────┬───────────────────────┤
│   ADMIN / OWNER  │      TRAINER         │       MEMBER          │
├──────────────────┼──────────────────────┼───────────────────────┤
│ • Dashboard      │ • Client Management  │ • Registration        │
│ • Staff Mgmt     │ • Plan Builder       │ • Plan Purchase       │
│ • Billing        │ • Performance Review │ • QR Check-In         │
│ • Notifications  │ • Schedule Manager   │ • Workout Logger      │
│ • Equipment      │                      │ • Progress Tracking   │
│ • Reports        │                      │ • Class Booking       │
└──────────────────┴──────────────────────┴───────────────────────┘
```

---

## 🏗️ System Architecture

```
┌─────────────────────────────────────────────────────┐
│                   CLIENT LAYER                       │
│         React.js Web Application (SPA)               │
│   Admin Dashboard │ Trainer Portal │ Member Portal   │
└────────────────────────┬────────────────────────────┘
                         │ REST API (JSON)
                         ▼
┌─────────────────────────────────────────────────────┐
│                  APPLICATION LAYER                   │
│              Java Spring Boot Backend                │
│                                                     │
│  ┌──────────────┐  ┌──────────────┐  ┌───────────┐ │
│  │Spring Security│  │Spring Data   │  │ Spring    │ │
│  │  (JWT Auth)  │  │  JPA (ORM)  │  │ Scheduler │ │
│  └──────────────┘  └──────────────┘  └───────────┘ │
│                                                     │
│  ┌──────────────┐  ┌──────────────┐  ┌───────────┐ │
│  │  REST APIs   │  │ Payment Svc  │  │  QR Svc   │ │
│  │(Spring MVC)  │  │              │  │           │ │
│  └──────────────┘  └──────────────┘  └───────────┘ │
└────────────────────────┬────────────────────────────┘
                         │
          ┌──────────────┴──────────────┐
          ▼                             ▼
┌─────────────────┐         ┌─────────────────────┐
│   PostgreSQL    │         │       MongoDB        │
│                 │         │                      │
│ • Users         │         │ • Workout Logs       │
│ • Memberships   │         │ • Diet Plans         │
│ • Transactions  │         │ • Session Data       │
│ • Bookings      │         │ • Metrics History    │
│ • Equipment     │         │                      │
└─────────────────┘         └─────────────────────┘
```

---

## 🛠️ Tech Stack

### Backend
- **Language:** Java 17+
- **Framework:** Spring Boot 3.x
- **Security:** Spring Security + JWT Authentication
- **ORM:** Spring Data JPA + Hibernate
- **Scheduler:** Spring Scheduler (cron jobs for renewals & reminders)
- **API:** Spring Web MVC (RESTful APIs)
- **Build Tool:** Maven / Gradle

### Frontend
- **Framework:** React.js 18+
- **State Management:** Redux Toolkit / Context API
- **Routing:** React Router v6
- **Charts:** Recharts / Chart.js
- **UI Library:** Material UI / Tailwind CSS
- **HTTP Client:** Axios
- **QR Code:** `react-qr-code`

### Databases
- **Primary DB:** PostgreSQL — for relational data (users, memberships, payments)
- **NoSQL:** MongoDB — for high-read workout logs and metrics

### DevOps & Tools
- **Containerization:** Docker + Docker Compose
- **API Docs:** Swagger / OpenAPI 3.0
- **Testing:** JUnit 5, Mockito, React Testing Library
- **CI/CD:** GitHub Actions

---

## 📁 Project Structure

```
fitforce/
│
├── 📂 backend/                          # Spring Boot Application
│   ├── src/main/java/com/fitforce/
│   │   ├── 📂 config/                   # Security, CORS, Swagger config
│   │   ├── 📂 controller/               # REST API Controllers
│   │   │   ├── AuthController.java
│   │   │   ├── MemberController.java
│   │   │   ├── TrainerController.java
│   │   │   ├── AdminController.java
│   │   │   ├── WorkoutController.java
│   │   │   └── BookingController.java
│   │   ├── 📂 service/                  # Business Logic Layer
│   │   ├── 📂 repository/               # JPA + MongoDB Repositories
│   │   ├── 📂 model/                    # Entity Classes
│   │   │   ├── User.java
│   │   │   ├── Membership.java
│   │   │   ├── WorkoutLog.java
│   │   │   ├── Booking.java
│   │   │   └── Equipment.java
│   │   ├── 📂 dto/                      # Data Transfer Objects
│   │   ├── 📂 scheduler/               # Cron Jobs (renewal reminders)
│   │   └── 📂 exception/               # Custom Exception Handlers
│   └── src/main/resources/
│       └── application.yml
│
├── 📂 frontend/                         # React Application
│   ├── src/
│   │   ├── 📂 pages/
│   │   │   ├── 📂 admin/               # Admin Dashboard Pages
│   │   │   ├── 📂 trainer/             # Trainer Portal Pages
│   │   │   └── 📂 member/              # Member Portal Pages
│   │   ├── 📂 components/              # Reusable UI Components
│   │   │   ├── WorkoutLogger/
│   │   │   ├── ProgressChart/
│   │   │   ├── QRScanner/
│   │   │   └── PlanBuilder/
│   │   ├── 📂 services/                # Axios API Calls
│   │   ├── 📂 store/                   # Redux Store
│   │   └── 📂 utils/                   # Helpers & Constants
│   └── package.json
│
├── 📂 docker/
│   ├── Dockerfile.backend
│   ├── Dockerfile.frontend
│   └── docker-compose.yml
│
└── 📄 README.md
```

---

## 🚀 Getting Started

### Prerequisites

Make sure you have the following installed:

- Java 17+
- Node.js 18+ & npm
- PostgreSQL 15+
- MongoDB 6+
- Docker & Docker Compose *(optional but recommended)*

---

### ⚡ Quick Start with Docker

```bash
# 1. Clone the repository
git clone https://github.com/your-org/fitforce.git
cd fitforce

# 2. Configure environment variables
cp .env.example .env
# Edit .env with your DB credentials and JWT secret

# 3. Start all services
docker-compose up --build

# ✅ App running at:
#   Frontend  → http://localhost:3000
#   Backend   → http://localhost:8080
#   Swagger   → http://localhost:8080/swagger-ui.html
```

---

### 🔧 Manual Setup

#### Backend (Spring Boot)

```bash
cd backend

# Configure your database in application.yml
nano src/main/resources/application.yml

# Build and run
./mvnw clean install
./mvnw spring-boot:run

# Backend starts at → http://localhost:8080
```

**`application.yml` key settings:**
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/fitforce_db
    username: your_db_user
    password: your_db_password

  data:
    mongodb:
      uri: mongodb://localhost:27017/fitforce_logs

jwt:
  secret: your_super_secret_jwt_key
  expiration: 86400000   # 24 hours in ms

app:
  qr:
    base-url: http://localhost:3000/checkin/
```

#### Frontend (React)

```bash
cd frontend

# Install dependencies
npm install

# Configure API base URL
echo "REACT_APP_API_URL=http://localhost:8080/api" > .env

# Start development server
npm start

# Frontend starts at → http://localhost:3000
```

---

## 📡 API Documentation

Full Swagger docs available at: `http://localhost:8080/swagger-ui.html`

### Core Endpoints

#### 🔐 Authentication
```
POST   /api/auth/register          Register new member
POST   /api/auth/login             Login (returns JWT)
POST   /api/auth/refresh           Refresh JWT token
```

#### 👤 Members
```
GET    /api/members                Get all members (Admin)
GET    /api/members/{id}           Get member profile
PUT    /api/members/{id}           Update member info
GET    /api/members/{id}/qr        Generate QR code for check-in
POST   /api/members/{id}/checkin   Process QR check-in
```

#### 🏋️ Workouts
```
POST   /api/workouts/log           Log a workout session
GET    /api/workouts/{memberId}    Get workout history
GET    /api/workouts/stats/{id}    Get progress statistics
```

#### 📅 Bookings
```
GET    /api/classes                List available classes
POST   /api/bookings               Book a class or PT session
GET    /api/bookings/{memberId}    View member bookings
DELETE /api/bookings/{id}          Cancel a booking
```

#### 💳 Memberships
```
GET    /api/memberships/plans      List all membership plans
POST   /api/memberships/purchase   Purchase a plan
PUT    /api/memberships/{id}/freeze  Freeze a membership
GET    /api/memberships/expiring   Get expiring memberships (Admin)
```

#### 📊 Admin
```
GET    /api/admin/dashboard        Dashboard summary stats
GET    /api/admin/revenue          Revenue reports
GET    /api/admin/equipment        Equipment status list
PUT    /api/admin/equipment/{id}   Update equipment status
```

---

## 🔐 Security

- **JWT-based Authentication** — Stateless auth with access & refresh tokens
- **Role-Based Access Control (RBAC)** — Routes and API endpoints secured by role (`ADMIN`, `TRAINER`, `MEMBER`)
- **Password Hashing** — BCrypt encryption for all stored passwords
- **CORS Configuration** — Whitelist-based cross-origin policy
- **QR Anti-Proxy** — Each QR token is time-bound and single-use per day

---

## ⏰ Scheduled Jobs

Powered by `@Scheduled` annotations in Spring:

| Job | Frequency | Description |
|---|---|---|
| Membership Expiry Check | Daily at 9 AM | Flags memberships expiring in 7 days |
| Renewal Reminder Email | Daily at 10 AM | Sends email to members 3 days before expiry |
| Auto-Expire Memberships | Daily at midnight | Sets `status = EXPIRED` for past-due memberships |
| Payment Due Notification | Weekly | Reminds members with pending invoices |

---


