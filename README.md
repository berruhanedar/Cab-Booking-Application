# 🚖 Cab Booking Application

A **Java Spring Boot** based comprehensive cab booking application.  
This project now supports **advanced booking flow, real-time location tracking, fare calculation, payment processing, and notification systems**.

---

## 📌 Implemented Features

### 1. User Management
- **User CRUD operations** with comprehensive DTO support
- **Role-based access control**: `CUSTOMER`, `DRIVER`, `ADMIN`
- **User status management** (ACTIVE, INACTIVE, SUSPENDED)
- **Role status management** with dynamic role changes
- **Address management** with user-address relationships

### 2. Driver Management
- **Complete driver lifecycle** management
- **Vehicle assignment system** (add/remove vehicles to drivers)
- **Real-time location updates** with coordinate tracking
- **Driver availability management** with enum states
- **Advanced search** using RSQL queries
- **Rating-based filtering** and average rating calculations
- **Pagination support** for large datasets

### 3. Vehicle Management
- **Vehicle CRUD with status control** (ACTIVE, INACTIVE, MAINTENANCE)
- **Advanced filtering** by vehicle type and energy type
- **License plate based lookup**
- **RSQL search capabilities**
- **Status transition management**

### 4. Booking Management
- **Complete booking lifecycle**: CREATE → ACCEPT → COMPLETE/CANCEL
- **Driver-vehicle assignment automation**
- **Multi-status tracking**: ACTIVE, COMPLETED, CANCELLED
- **User-specific and driver-specific booking views**
- **Cancellation tracking** with cancelled-by enum (USER, DRIVER, SYSTEM)
- **Available bookings listing** for driver acceptance

### 5. Address Management
- **Full CRUD operations** with pagination
- **Advanced RSQL search** capabilities
- **DTO-based request/response handling**

### 6. Contact Form Management
- **Contact form lifecycle** management
- **Duplicate prevention** by email validation
- **RSQL search and pagination**
- **Update and delete operations**

### 7. Authentication & Authorization
- **JWT-based authentication** with refresh tokens
- **Secure logout mechanism**
- **User registration and login** with comprehensive DTOs
- **Token refresh functionality**

### 8. Real-time Location Services
- **Driver location tracking** with coordinate updates
- **Distance calculation** in kilometers
- **Nearest driver discovery** based on user location
- **ETA estimation** based on distance
- **Location-based queries**

### 9. Fare Calculation System
- **Dynamic fare calculation** based on booking parameters
- **Integration with booking lifecycle**
- **Flexible pricing strategies**

### 10. Payment Processing
- **Payment processing** with dedicated request/response DTOs
- **Booking-based payment automation**
- **Refund processing** for cancelled bookings
- **Payment details retrieval**

### 11. Rating & Review System
- **Rating creation** for completed bookings
- **Driver rating aggregation**
- **Rating updates and management**
- **Booking-specific rating tracking**

### 12. Notification System
- **Multi-channel notifications**: Email, SMS, Push
- **Booking lifecycle notifications**:
  - Booking created
  - Booking accepted
  - Booking completed
  - Booking cancelled
- **User-targeted push notifications**

### 13. Context Management
- **Current user context** retrieval
- **Role-based access checking**
- **Session management utilities**

---

## 🏗 Service Architecture

### Core Services
| Service | Responsibility |
|---------|----------------|
| `UserService` | User management, role changes, status updates |
| `DriverService` | Driver operations, vehicle assignment, location, availability |
| `VehicleService` | Vehicle management, filtering, status control |
| `BookingService` | Booking lifecycle, driver assignment, status management |
| `AddressService` | Address CRUD, search, pagination |
| `ContactService` | Contact form management, validation |
| `AuthService` | Authentication, JWT, refresh tokens |

### Advanced Services
| Service | Responsibility |
|---------|----------------|
| `LocationService` | Real-time location, distance calculation, ETA |
| `FareCalculationService` | Dynamic fare calculation |
| `PaymentService` | Payment processing, refunds |
| `RatingService` | Rating management, driver reviews |
| `NotificationService` | Multi-channel notifications |
| `BookingNotificationService` | Booking-specific notifications |
| `ContextService` | User context and session management |

---

## 🎯 Implementation Status

### ✅ **Completed - Phase 1**
- **User Management System** with role-based access
- **Driver & Vehicle Management** with advanced relationships
- **Booking System** with complete lifecycle
- **Authentication & Authorization** with JWT
- **Address & Contact Management**
- **Real-time Location Services**
- **Fare Calculation Engine**
- **Payment Processing System**
- **Rating & Review System**
- **Multi-channel Notification System**
- **Context & Session Management**

### 🔄 **In Development - Phase 2**
- Admin dashboard analytics
- Advanced reporting system
- Campaign management
- Enhanced notification templates

### 📋 **Planned - Phase 3**
- Kafka integration for real-time events
- Redis caching implementation
- Advanced analytics and BI reporting
- Mobile app development

---

## 🛠 Technical Stack

### Backend
- **Java 21** with **Spring Boot 3.x**
- **Spring Security** with JWT authentication
- **Spring Data JPA** with Hibernate
- **PostgreSQL** for primary data storage
- **Maven** for dependency management

### Architecture & Patterns
- **DTO Pattern** for request/response handling
- **Service Interface Pattern** for abstraction
- **Enum-based Status Management**
- **RSQL for Advanced Search**
- **Pagination Support** for large datasets

### Utilities & Libraries
- **MapStruct** for object mapping
- **Lombok** for boilerplate reduction
- **Swagger/OpenAPI** for documentation

### Planned Integrations
- **Redis** for caching and session storage
- **Apache Kafka** for event streaming
- **Docker** for containerization

---

## 📊 Service Method Summary

### Booking Service
- `createBooking()` → `acceptBooking()` → `completeBooking()/cancelBooking()`
- User/driver specific booking listings
- Available bookings for driver acceptance

### Location Service
- Real-time driver location updates
- Distance calculation and ETA estimation
- Nearest driver discovery

### Notification Service
- Lifecycle-based booking notifications
- Multi-channel support (email, SMS, push)

### Payment & Rating
- Automated payment processing
- Rating system integrated with bookings

---

## 🔄 Development Progress

| Phase | Status | Completion |
|-------|--------|------------|
| Core Services | ✅ **Completed** | 100% |
| Advanced Features | ✅ **Completed** | 100% |
| Admin Dashboard | 🔄 **In Progress** | 70% |
| Real-time Events | 📋 **Planned** | 0% |

---

## 🚀 Next Steps

1. **Admin Dashboard Implementation**
2. **Kafka Integration** for real-time events
3. **Redis Caching** for performance optimization
4. **Advanced Analytics** and reporting
5. **Mobile Application** development

---

**Made with ❤️ by Berru Hanedar**
