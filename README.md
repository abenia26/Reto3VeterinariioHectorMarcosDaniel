# Reto3VeterinariioHectorMarcosDaniel

# 🐾 Reto Veterinario

Aplicación de escritorio en **Java** para la gestión de una clínica veterinaria. Permite consultar clientes, mascotas e historial médico conectándose a una base de datos MySQL mediante JDBC.

---

## 📁 Estructura del proyecto

```
src/
├── app/
│   └── Main.java               # Punto de entrada
├── dao/
│   ├── GenericDAO.java         # Interfaz genérica CRUD
│   ├── ClienteDAO.java
│   ├── MascotaDAO.java
│   ├── HistorialDAO.java
│   ├── TratamientoDAO.java
│   └── VeterinarioDAO.java
├── modelo/
│   ├── Persona.java
│   ├── Cliente.java
│   ├── Veterinario.java
│   ├── Mascota.java
│   ├── Historial.java
│   └── Tratamiento.java
└── util/
    └── ConexionBD.java         # Gestión de la conexión JDBC
```

---

## ⚙️ Configuración de la base de datos

Edita `util/ConexionBD.java` con tus credenciales:

```java
private static final String URL  = "jdbc:mysql://localhost:3306/dam_programacion";
private static final String USER = "dam";
private static final String PASS = "1234";
```

> [!TIP]
> Asegúrate de tener el conector **MySQL JDBC Driver** añadido al build path del proyecto en Eclipse.

---

## 🚀 Funcionalidades del Main

### Ejercicio 2 — Clientes y sus mascotas

1. Lista todos los clientes registrados en la BD
2. Pide un `id_cliente` por teclado
3. Muestra todas las mascotas de ese cliente

### Ejercicio 3 — Mascotas e historial médico

1. Lista todas las mascotas registradas
2. Pide un `id_mascota` por teclado
3. Muestra el historial clínico completo de esa mascota

---

## 🗂️ Patrón DAO

Todos los DAOs implementan la interfaz genérica:

```java
public interface GenericDAO<T> {
    boolean insertar(T objeto);
    List<T> obtenerTodos();
    T obtenerPorId(int id);
    boolean actualizar(T objeto);
    boolean eliminar(int id);
}
```

> [!TIP]
> Cada DAO tiene un método privado `mapearFila(ResultSet rs)` que convierte una fila SQL en un objeto Java. Si cambias la BD, solo tienes que tocar ese método.

---

## ▶️ Cómo ejecutar

1. Clona o importa el proyecto en **Eclipse** como *Java Project*
2. Añade el driver MySQL al build path
3. Asegúrate de que el servidor MySQL está corriendo
4. Ejecuta `Main.java`

> [!WARNING]
> Si la conexión falla, revisa que el servicio MySQL esté activo y que las credenciales en `ConexionBD.java` sean correctas.

> [!NOTE]
> El proyecto usa **Java SE** puro con JDBC, sin frameworks externos como Hibernate o Spring.

---

## 👥 Autores

Desarrollado por **Héctor, Marcos y Daniel** — 1º DAM
