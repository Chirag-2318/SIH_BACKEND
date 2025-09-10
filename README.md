# SIH_BACKEND
Spring Boot implementation of Tourist Safety Project
# 🛡️ Smart Tourist Safety Monitoring & Incident Response System (Backend)


---

## 🚀 Features

- ✅ **JWT-based Authentication & Authorization**  
- ✅ **Tourist Digital ID Management (Blockchain-ready)**  
- ✅ **Tourist Profile & Trip Details APIs**  
- ✅ **Real-time Location Tracking & Geo-fencing**  
- ✅ **Emergency SOS / Panic Button Alerts**  
- ✅ **Police & Authority Integration APIs**  
- ✅ **WebSocket Support for Live Alerts & Location Updates**  
- ✅ **Redis Caching for Sessions & Fast Lookups**  
- ✅ **Swagger/OpenAPI Documentation**  

---

Tourist Safety System 🌍
This project is a comprehensive Tourist Safety System built with Java. It utilizes various components to provide a robust solution for tracking, alerting, and managing tourist safety. The system is designed with a layered architecture, including controllers, models, and services, to ensure scalability and maintainability.

**Project Structure 📁**
The project is organized into the following key packages:

**com.tourism.safety.tourist_safety_system**_

**Controller: 👩‍💼 Contains the REST API endpoints and business logic for various functionalities.**

AI_ML_AnomalyDetectionController: Manages AI/ML-based anomaly detection for unusual tourist behavior. 🤖

AuthenticationAuthorizationController: Handles user authentication and authorization. 🔑

BlockchainFunctionsController: Manages blockchain-related functions for secure and transparent data. 🔗

CommunicationController: Handles communication with tourists and authorities (e.g., SMS, push notifications). 💬

DashboardAnalyticsController: Provides endpoints for dashboard and analytics data. 📊

DigitalIDManagementController: Manages digital IDs for tourists. 💳

EmergencyAlertController: Handles the processing and dispatching of emergency alerts. 🚨

GeofencingController: Manages geofencing for specific tourist locations. 📍

IntegrationController: Integrates with third-party systems. 🔄

IoTDeviceIntegrationController: Handles data from IoT devices. 📡

LocationTrackingController: Manages real-time location tracking of tourists. 🗺️

PoliceAuthorityController: Provides specific functionality for police authorities. 🚓

ReportingComplianceController: Manages reporting and compliance-related data. 📋

SystemAdministrationController: Handles administrative tasks for the system. ⚙️

TouristRegistrationProfileController: Manages tourist registration and profiles. 📝

WebSocketEndpointsController: Provides real-time communication via WebSockets. ⚡

**Model: 🧩 Defines the data structures (entities) used throughout the application.**

Alert_Incident: Represents an emergency or safety incident.

LocationData: Stores location information.

TouristProfile: Represents a tourist's profile and information.

**Repository: 💾 Manages data access and persistence, likely using Spring Data JPA.**

MainRepo: The main repository for data operations.

**Security: 🛡️ Contains security-related configurations and logic.**

**Service: 🛠️ Contains the core business logic, separate from the controllers.**

SmartTouristSafetyApplication: The main application class to run the Spring Boot application. 🚀
