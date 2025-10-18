# 🍔 Sistema de gestión de un restaurante (En desarrollo)

Aplicación web fullstack para la gestión completa de un restaurante, desarrollada con Spring (backend) y React (frontend).  
Permite gestionar reservas, comandas, usuarios y autenticación con JWT, todo desplegado mediante Docker.

---

## 🚀 Características principales

### 🧑‍💼 Gestión de usuarios
- Registro e inicio de sesión mediante Spring Security + JWT.
- Roles de usuario: administrador, empleado y cliente.
- Protección de endpoints según permisos.

### 🍽️ Gestión de comandas
- Creación de comandas asociadas a una mesa.
- Añadir o eliminar platos de una comanda.
- Cálculo automático del precio total.
- Persistencia en base de datos con JPA / Hibernate.

### 📅 Gestión de reservas
- Registro y consulta de reservas.
- Asociación con clientes y mesas.
- Validación de fechas y disponibilidad.

### 🧾 Sistema de menús
- Base de datos de platos (entrantes, principales, postres y bebidas).
- Cada plato incluye nombre, descripción, precio y categoría.

---

## 🏗️ Arquitectura del proyecto

El proyecto está dividido en dos repositorios:

| **Backend** | [🔗 gestor-restaurante-backend](https://github.com/JaviDev707/GestorDeRestaurante-Backend) | API REST con Spring Boot, JPA, JWT y MySQL |
| **Frontend** | [🔗 gestor-restaurante-frontend](https://github.com/JaviDev707/GestorDeRestaurante-Frontend) | Web del restaurante para ver la carta y hacer reservas |

---

## ⚙️ Tecnologías utilizadas

### Backend
- Java 21  
- Spring Boot 3  
- Spring Security + JWT  
- Spring Data JPA (MySQL)
- Maven  
- Docker / Docker Compose  

### Frontend
- React + Vite  
- Axios (comunicación con la API)  
- TailwindCSS  
- JWT Storage en localStorage  

---

## 🐳 Despliegue con Docker

### ▶️ Ejecución

1. Clona el repositorio del backend

2. Crea el archivo .env en la raíz con tus variables siguiendo .env.example

3. Levanta los contenedores: docker-compose up --build

4. La API estará disponible en: http://localhost:8080


## Desarrollado por JaviDev707