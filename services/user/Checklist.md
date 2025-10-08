
## ✅ User Service Development Checklist

### 1. **Project Setup**

* [ x ] Create a new Spring Boot project (via Spring Initializr).
* [ x ] Include dependencies:

  * `Spring Web`
  * `Spring Data JPA`
  * `Spring Security` (for auth, optional first pass)
  * `Spring Validation`
  * `PostgreSQL Driver` (or MySQL, depending on choice)
  * `Lombok` (optional but convenient).
* [ x ] Configure `application.yml` with DB connection, server port.

---

### 2. **Database Design**

* [x ] Define schema: `User` table. Example fields:

  * `id (UUID)`
  * `username`
  * `email`
  * `password` (hashed)
  * `role` (USER, ADMIN)
  * `created_at` / `updated_at`
* [x] Write JPA entity class for `User`.
* [x ] Implement repository interface with `JpaRepository<User, UUID>`.

---

### 3. **Business Logic Layer**

* [x ] Create `UserService` class for business operations:

  * Create new user.
  * Get user by ID/username/email.
  * Update user details.
  * Delete user.
* [x ] Add password hashing (BCrypt).

---

### 4. **API Layer**

* [ x] Create `UserController` with REST endpoints:

  * `POST /users` → Register new user.
  * `GET /users/{id}` → Fetch user by ID.
  * `PUT /users/{id}` → Update user.
  * `DELETE /users/{id}` → Delete user.
  * (Optional) `GET /users` → List all users (admin only).

---

### 5. **Validation & Security**

* [x] Use DTOs + validation annotations (`@NotBlank`, `@Email`, etc.).
* [x] Add Spring Security (basic JWT authentication):

  * `POST /auth/register`
  * `POST /auth/login`
  * Protect `/users/**` endpoints.
* [x] Store only hashed passwords.

---

### 6. **Testing**

* [x] Write unit tests for `UserService`.
* [x] Write integration tests for `UserController`.
* [x]
---

### 7. **Dockerization**

* [x] Create `Dockerfile` for the service.
* [x] Add `docker-compose.yml` with DB + User Service.
* [x] Ensure it runs locally.

---

### 8. **Observability**

* [ ] Add logging with SLF4J.
* [ ] Add basic health check (`/actuator/health`).
* [ ] Expose metrics (`/actuator/prometheus`).

---

### 9. **Future Integration**

* [ ] Event publishing (e.g., `UserRegistered` → Message Broker).
* [ ] Add role-based access (for ADMIN/USER).
* [ ] Connect with Order Service (when ready).

