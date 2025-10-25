# **CRUDACTIVITY — Crudzaso TinyTasks**

Crudzaso lanza **TinyTasks**, una microaplicación para poner en práctica la estructura mínima de un proyecto con **Spring Boot** y **front nativo**.
El reto consiste en construir una API REST con **capas pequeñas** y datos **en memoria**, conectada a una interfaz **HTML + JS**, usando **Bootstrap o Tailwind**.
Además, se deben incluir **pruebas unitarias con JUnit** que validen la lógica central de la aplicación.

El enfoque es simple: claridad, coherencia y funcionalidad.

---

### **Contexto**

TinyTasks permite gestionar una lista básica de tareas, con operaciones para listar, crear, alternar el estado y eliminar.
El sistema no usa base de datos: los datos viven en memoria.
El objetivo es practicar un flujo completo: **Front → API → Lógica → Datos**, asegurando que cada capa cumpla su función y sea testeable.

---

### **Requerimientos técnicos**

* **Lenguaje:** Java 21
* **Framework:** Spring Boot (Web)
* **Frontend:** HTML + JS (con Bootstrap **o** Tailwind)
* **Persistencia:** En memoria (colección interna, sin DB ni H2)
* **Pruebas:** JUnit 5
* **Naming:** todo en inglés (clases, endpoints, variables, UI)
* **Ejecución:** backend en `http://localhost:8080`, front servido localmente (por ejemplo, `http://localhost:5500`)
* **CORS:** habilitado para el host del front

Que cumpla con principos solid, dry, kiss, y demas buenas practicas al momento de programar. 

---

### **Requerimientos funcionales**

**Endpoints:**

* `GET /api/todos` — devuelve la lista de tareas.
* `POST /api/todos` — crea una nueva tarea.

  * Body: `{ "title": "string (required, ≥3 chars)" }`
* `PUT /api/todos/{id}/toggle` — alterna el estado `done`.
* `DELETE /api/todos/{id}` — elimina una tarea.

**Formato de respuesta:**

```json
{
  "id": 1,
  "title": "Learn Spring Boot",
  "done": false
}
```

**Errores:**

* 400 → `{ "error": "Title is required" }`
* 404 → `{ "error": "Not found" }`

---

### **Estructura esperada**

```
crudzaso-tinytasks/
 ├─ backend/
 │   └─ src/main/java/com/crudzaso/tinytasks/
 │        ├─ controller/
 │        ├─ service/
 │        ├─ repository/
 │        ├─ model/
 │        └─ config/
 ├─ frontend/
 │   ├─ index.html
 │   ├─ style.css (opcional)
 │   └─ app.js
 └─ README.md
```

---

### **Historias de usuario y criterios de aceptación**

**HU-01 — Listar tareas**
Como usuario quiero ver todas las tareas registradas para saber qué tengo pendiente.

* Given: la API está activa
* When: el front ejecuta `GET /api/todos`
* Then: se muestra una lista con todas las tareas actuales.

---

**HU-02 — Crear tarea**
Como usuario quiero crear una nueva tarea para mantener mis pendientes organizados.

* Given: un campo de texto con el título de la tarea
* When: ingreso un texto válido y presiono **Add**
* Then: se ejecuta `POST /api/todos` y se agrega a la lista.
* If: el título es inválido
* Then: la API responde con 400 y el front muestra un mensaje de error.

---

**HU-03 — Alternar estado**
Como usuario quiero marcar o desmarcar una tarea como completada para visualizar mi progreso.

* When: presiono **Toggle**
* Then: se ejecuta `PUT /api/todos/{id}/toggle` y el estado cambia en la interfaz.
* If: la tarea no existe
* Then: la API responde con 404 y se muestra un aviso.

---

**HU-04 — Eliminar tarea**
Como usuario quiero eliminar una tarea que ya no necesito.

* When: presiono **Delete**
* Then: se ejecuta `DELETE /api/todos/{id}` y la tarea desaparece de la lista.
* If: el id no existe
* Then: la API responde 404 con `{ "error": "Not found" }`.

---

**HU-05 — Pruebas unitarias**
Como desarrollador quiero validar la lógica de negocio para asegurar que el sistema funcione incluso después de cambios.

* Given: el proyecto configurado con JUnit 5
* When: ejecuto los tests
* Then: todas las pruebas del **Service** y el **Repository** pasan exitosamente.
* Must have: al menos un caso positivo y uno negativo para **create**, **toggle** y **delete**.

---

### **Casos de prueba mínimos (JUnit 5)**

| Módulo     | Caso de prueba  | Escenario positivo                        | Escenario negativo                   |
| ---------- | --------------- | ----------------------------------------- | ------------------------------------ |
| Service    | Crear tarea     | Título válido crea tarea con `done=false` | Título vacío o corto lanza excepción |
| Service    | Alternar estado | Cambia `done` de false→true o viceversa   | ID inexistente devuelve vacío        |
| Service    | Eliminar tarea  | Borra elemento y lista refleja el cambio  | ID inexistente devuelve false        |
| Repository | Generar IDs     | IDs autoincrementales y únicos            | —                                    |
| Repository | Buscar por ID   | Retorna `Optional` con valor              | Retorna vacío                        |

---
