# 🧩 Gestión de Préstamos — JDBC + DAO + Arquitectura en 3 Capas

Este proyecto implementa un sistema básico de **gestión de préstamos** utilizando:

✔ JDBC Driver  
✔ Patrón de diseño **DAO**  
✔ Arquitectura por capas (**Dominio – Datos – Presentación**)  
✔ Programación Orientada a Objetos (POO)  
✔ Operaciones CRUD probadas por consola  

---

# 🏗 Arquitectura del Proyecto

```
/src
 ├── dominio/
 │     └── Prestamo.java
 │
 ├── datos/
 │     ├── IPrestamoDAO.java
 │     └── PrestamoDAO.java
 │
 ├── conexion/
 │     └── Conexion.java
 │
 └── presentacion/
       └── PrestamoApp.java
```

---

# 🗃 1. Capa de Conexión — JDBC

En esta capa se implementa la clase **Conexion**, encargada de:

- Registrar el **driver JDBC**
- Establecer la conexión con MySQL
- Probar el establecimiento efectivo de la conexión
- Retornar el objeto `Connection` para usarlo en el DAO

Incluye manejo de excepciones y cierre adecuado de recursos.

---

# 👤 2. Capa de Dominio — Entidad Prestamo

La clase `Prestamo` representa la estructura del usuario:

- Atributos del prestamo  
- Constructores para:
  - Buscar / eliminar (solo ID)
  - Crear (datos)
  - Modificar (ID + datos)
- Métodos `get` y `set`
- Métodos sobrescritos:
  - `toString()`  
  - `equals()`  
  - `hashCode()`  

Esto permite un mejor manejo de los objetos y favorece la comparación entre instancias.

---

# 💾 3. Capa de Datos — DAO

Incluye:

### ✔ Interfaz `IPrestamoDAO`
Define los métodos CRUD:

- `listarPrestamos()`
- `buscarPrestamoPorId(Prestamo prestamo)`
- `agregarPrestamo(Prestamo prestamo)`
- `modificarPrestamo(Prestamo prestamo)`
- `eliminarPrestamo(Prestamo prestamo)`

### ✔ Implementación `PrestamoDAO`
Usando:

- `Connection`
- `PreparedStatement`
- `ResultSet`

Se desarrollan los métodos CRUD accediendo directamente a la base de datos.

Todos los métodos fueron probados por consola.

---

# 🎮 4. Capa de Presentación — Consola

La aplicación presenta un menú interactivo:

```
1. Listar Préstamos
2. Buscar Préstamo
3. Agregar Préstamo
4. Modificar Préstamo
5. Eliminar Préstamo
6. Salir
```

El menú utiliza un objeto del servicio/DAO para ejecutar cada operación.

---

# 🚀 Tecnologías Utilizadas

- Java 17+
- MySQL 8
- JDBC Driver
- Patrón DAO
- Arquitectura por capas
- Programación Orientada a Objetos

---

# 📌 Objetivo del Proyecto

Este repositorio forma parte de mi ruta de aprendizaje backend con Java, donde desarrollo un CRUD por cada módulo del sistema **Gestión de Biblioteca**, iniciando con JDBC antes de avanzar hacia:

➡ Spring Boot  
➡ Spring MVC + Thymeleaf

---

# 🙌 Autor

**morocho**  
📧 Correo: *portafoliomoro@gmail.com*  
🔗 GitHub: *portafoliomoro-moro*  

---

