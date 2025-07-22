# 🥗 **NutriZen** 📊🔥

***Aplicación web para el análisis nutricional, gestión de recetas y recomendaciones saludables***

🎯 **Registra tus ingredientes, construye recetas personalizadas y recibe análisis nutricionales inteligentes con sugerencias para mejorar tu salud.**

---

## 📌 **Descripción**

**NutriZen** es una plataforma web orientada al bienestar alimenticio. Permite a los usuarios registrar ingredientes, crear recetas personalizadas, analizar su información nutricional y obtener sugerencias saludables, incluyendo recomendaciones físicas para equilibrar su dieta.

La aplicación está construida con:

* 💻 Backend robusto en **Java + Spring Boot**
* 🛡️ Autenticación segura con **JWT**
* 📊 Análisis nutricional automatizado
* 📦 Persistencia con **Hibernate + PostgreSQL**
* 🎨 Frontend moderno basado en **SB Admin 2 + HTML + JS**

---

## 🎯 **Objetivos del Proyecto**

✅ Permitir la gestión de ingredientes con datos nutricionales.  
✅ Crear recetas asociando múltiples ingredientes.  
✅ Analizar el perfil nutricional total de una receta.  
✅ Brindar advertencias cuando se superen umbrales no saludables.  
✅ Sugerir ejercicios físicos para compensar calorías consumidas.  
✅ Garantizar una experiencia fluida, segura e intuitiva.

---

## 🛠️ **Tecnologías Utilizadas**

### ⚙️ **Backend:**

* ![Java](https://img.shields.io/badge/Java-17-blue?logo=openjdk)
* ![Spring Boot](https://img.shields.io/badge/Spring_Boot-Framework-success?logo=springboot)
* ![Hibernate](https://img.shields.io/badge/Hibernate-JPA-59666C?logo=hibernate)
* ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-336791?logo=postgresql)
* ![JWT](https://img.shields.io/badge/JWT-Security-orange?logo=jsonwebtokens)

### 🎨 **Frontend:**

* ![HTML](https://img.shields.io/badge/HTML-5-orange?logo=html5)
* ![JavaScript](https://img.shields.io/badge/JavaScript-ES6-yellow?logo=javascript)
* ![Bootstrap](https://img.shields.io/badge/Bootstrap-5-purple?logo=bootstrap)
* ![SB Admin 2](https://img.shields.io/badge/SB_Admin_2-Template-lightgrey)

---

## 📂 **Estructura del Proyecto**

```

NutriZen/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/nutrizen/
│   │   │       ├── controllers/       # Controladores REST
│   │   │       ├── models/            # Entidades JPA (Ingrediente, Receta, Usuario)
│   │   │       ├── services/          # Lógica de negocio
│   │   │       ├── repositories/      # Interfaces JPA para DB
│   │   │       └── security/          # Configuración de JWT
│   │   └── resources/
│   │       ├── application.properties # Configuración de Spring
│   │       └── templates/             # Plantillas HTML (SB Admin 2)
├── pom.xml                            # Dependencias Maven
└── README.md

````

---

## 🚀 **Instalación y Ejecución**

### 📌 Requisitos Previos

* Java 17  
* Maven 3.9+  
* Docker

---

### 🐳 **Ejecución Completa con Docker Compose (PostgreSQL + Backend)**

Puedes levantar toda la aplicación fácilmente con Docker Compose.

#### ▶️ 1. Archivo `docker-compose.yml`:

```yaml
version: '3.8'

services:
  db:
    image: postgres:15
    container_name: nutrizen-db
    environment:
      POSTGRES_USER: nutrizen_user
      POSTGRES_PASSWORD: nutrizen_pass
      POSTGRES_DB: nutrizen
    ports:
      - "5432:5432"
    volumes:
      - nutrizen-data:/var/lib/postgresql/data
    networks:
      - nutrizen-net

  app:
    build: .
    container_name: nutrizen-backend
    ports:
      - "8080:8080"
    depends_on:
      - db
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/nutrizen
      SPRING_DATASOURCE_USERNAME: nutrizen_user
      SPRING_DATASOURCE_PASSWORD: nutrizen_pass
    networks:
      - nutrizen-net

volumes:
  nutrizen-data:

networks:
  nutrizen-net:
````

---

#### ▶️ 2. Ejecutar la aplicación:

```bash
docker-compose up --build
```

Esto levantará automáticamente la base de datos PostgreSQL y el backend Java.

---

#### 🛠️ Variables inyectadas:

```env
SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/nutrizen
SPRING_DATASOURCE_USERNAME=nutrizen_user
SPRING_DATASOURCE_PASSWORD=nutrizen_pass
```

Tu `application.properties` debe tener:

```properties
spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
```

---

#### 🌍 Acceso a la app:

* Backend: [http://localhost:8080](http://localhost:8080)

#### 🛑 Para detener los contenedores:

```bash
docker-compose down
```

---

### ⚙️ Ejecutar el backend localmente (opcional)

```bash
# Clona el repositorio
git clone https://github.com/tunombre/nutrizen.git
cd nutrizen

# Asegúrate de que PostgreSQL esté corriendo en tu máquina
# Ejecuta el proyecto
./mvnw spring-boot:run
```

---

## 📊 **Principales Funcionalidades**

🔸 Registro y gestión de ingredientes con datos nutricionales
🔸 Creación de recetas a partir de múltiples ingredientes
🔸 Cálculo automático de nutrientes totales
🔸 Alertas si se superan valores como sodio, azúcar, grasa
🔸 Recomendaciones de ejercicio físico para compensar calorías
🔸 CRUD completo con persistencia y autenticación segura

---

## 🧪 **Ejemplo de Uso**

1. Inicia sesión o regístrate en la plataforma.
2. Registra ingredientes como “Avena”, “Leche”, “Manzana”, etc.
3. Crea una receta nueva seleccionando tus ingredientes.
4. El sistema calcula calorías totales y te indica si hay exceso de grasa/sodio.
5. Recibes sugerencias como: *“30 min de caminata para quemar esta receta”*.

---

## 🧱 **Dependencias Clave**

```xml
<!-- pom.xml -->
<dependencies>
  <dependency>spring-boot-starter-web</dependency>
  <dependency>spring-boot-starter-security</dependency>
  <dependency>spring-boot-starter-data-jpa</dependency>
  <dependency>postgresql</dependency>
  <dependency>jjwt</dependency>
  <dependency>spring-boot-starter-thymeleaf</dependency>
</dependencies>
```

---

## 🌟 **Mejoras Futuras**

🚀 Panel de métricas del usuario
🚀 Integración con APIs externas de nutrición (OpenFoodFacts, Spoonacular)
🚀 Dashboard con consumo histórico y evolución del usuario
🚀 Soporte para objetivos personalizados de dieta
🚀 Modo offline con almacenamiento local

---

## 🤝 **Contribuciones**

¡Tu aporte es bienvenido!

1. Haz un fork 🍴
2. Crea una rama `feature/nueva-funcionalidad`
3. Realiza tus cambios y haz commit
4. Envía un Pull Request ✨

---

## 🧑‍💻 **Autores**

👨‍💻 Erik Herrera
👨‍💻 Donovan Pilicita
👩‍💻 Fernanda Llano

---

## 📃 **Licencia**

Este proyecto está bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.

---

🍏 **NutriZen — Comienza a cuidar lo que comes, de forma inteligente.**

```

