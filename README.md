# 🚖 Cab Booking Application

A **Java Spring Boot** based cab booking application.  
This project currently supports **basic CRUD operations** and **user management**, with future enhancements planned.

---

## 📌 Features

### 1. User Management
- Create, update, and delete users.
- Role-based access: `CUSTOMER`, `DRIVER`, `ADMIN`.
- Add and manage addresses.

### 2. Driver Management
- Driver CRUD operations.
- Assign/remove vehicles (Driver ↔ Vehicle relationship).
- Update driver location and availability.
- Driver rating support.

### 3. Vehicle Management
- Vehicle CRUD operations.
- Filter by type and energy type.
- Update vehicle status (`ACTIVE`, `INACTIVE`).

### 4. Booking Management
- Create, update, delete bookings.
- Assign drivers and vehicles.
- Booking status: `ACTIVE`, `COMPLETED`, `CANCELLED`.
- Update driver stats after completing a ride.

### 5. Address Management
- Address CRUD operations.
- Pagination and RSQL search support.

### 6. Contact Form
- Submit new contact forms.
- Prevent duplicate submissions from the same email.

---

## ⚡ Future Plans (Phase 2+)
- Admin panel & advanced reporting.
- Rating & Review system.
- Payment & invoice management.
- Notification system: SMS, email, push notifications.
- Real-time driver location tracking (Kafka or Redis).
- Advanced booking features: distance, estimated time, fare calculation.
- Optional vehicle rental feature.
- Audit fields and logging.

---

## 🏗 Project Structure

- `UserService` → User management  
- `DriverService` → Driver management  
- `VehicleService` → Vehicle management  
- `BookingService` → Booking management  
- `AddressService` → Address management  
- `ContactService` → Contact form management  
- `AuthService` → Authentication & Authorization  
- `RatingService`, `PaymentService`, `NotificationService`, `ContextService` → Planned future services

---

## 🎯 Development Roadmap

### Phase 1 – Core Auth & User System
- User Registration/Login (JWT + Password Hash)
- Role & permission management
- Profile management
- Email verification
- Driver registration and management
- Booking system (Customer → Driver matching)
- Ride pricing & payment integration
- Real-time location tracking

### Phase 2 – Admin & Analytics
- Admin dashboard
- User/Driver analytics
- Reporting system
- Campaign & promotion management

### Phase 3 – Extra Features
- Favorite addresses
- Rating & review system
- Push notifications
- Real-time events via Kafka

---

## 📦 Current Service Summary

| Service | Purpose |
|---------|---------|
| `UserService` | User CRUD & role management |
| `DriverService` | Driver CRUD, location, availability, rating |
| `VehicleService` | Vehicle CRUD, filters, status updates |
| `BookingService` | Booking CRUD, driver/vehicle assignment, ride completion |
| `AddressService` | Address CRUD, search, pagination |
| `ContactService` | Contact form submission & validation |
| `AuthService` | Authentication & authorization |
| `RatingService` | Future: driver rating management |
| `PaymentService` | Future: payments and invoices |
| `NotificationService` | Future: push/email/SMS notifications |
| `ContextService` | Future: app context/session management |

---

## 📌 Notes
- Contact form currently allows **one submission per email**. This will be made more flexible in future updates.  
- User role changes will be restricted to **ADMIN users only** in upcoming releases.  
- Optional features like **vehicle rental** may be added later.

---

## 🛠 Tech Stack
- **Java 21** & **Spring Framework / Spring Boot**
- **Spring Security + JWT**
- **Spring Data JPA & Hibernate**
- **PostgreSQL** (database)
- **Docker** (containers)
- **Redis** (caching & real-time support, planned)
- **Apache Kafka** (real-time events, planned)
- **MapStruct** (DTO mapping)
- **Lombok**
- **Maven** (build & dependency management)
- **Swagger** (API documentation)

---

## 📅 Development Timeline (Suggested)
| Week | Task |
|------|------|
| 1-2 | Auth & User Management → Alpha Test |
| 3-4 | Booking System → Beta Test |
| 5-6 | Admin Dashboard → Production |
| 7+  | Advanced Features & Scaling |

---

**Made with ❤️ by Berru Hanedar**  
