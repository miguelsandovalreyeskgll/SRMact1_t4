# SRMact1_t4 — Introducción a Spring Boot

**Actividad 1 · Tema 4 — Programación Web**

**Estudiante:** Sandoval Reyes Miguel  
**Tecnologías:** Java 17, Spring Boot 4.1, Spring Web MVC y Gradle  
**Puerto de ejecución:** `8082`

## Descripción

Proyecto introductorio desarrollado con **Spring Boot** para implementar y publicar una API REST sencilla. La aplicación cuenta con dos controladores y tres endpoints HTTP de tipo `GET`, que proporcionan respuestas de texto, una lista en formato JSON y un valor numérico.

El proyecto no requiere base de datos, API externa ni interfaz gráfica. Las peticiones se pueden realizar desde un navegador web, Postman o la terminal mediante `curl`.

## Estructura del proyecto

```text
src/
└── main/
    ├── java/com/srm/actividad1/
    │   ├── Actividad1Application.java
    │   └── controller/
    │       ├── ProyectoController.java
    │       └── MateriasController.java
    └── resources/
        └── application.properties
build.gradle
gradlew.bat
README.md
```

- **`Actividad1Application.java`:** clase principal que inicia la aplicación Spring Boot.
- **`ProyectoController.java`:** expone el endpoint de identificación del proyecto.
- **`MateriasController.java`:** expone los endpoints de listado y conteo de materias.
- **`application.properties`:** establece el nombre de la aplicación y el puerto HTTP.
- **`build.gradle`:** administra las dependencias y la configuración de compilación.

## Endpoints disponibles

| Método | Ruta | Descripción | Respuesta de ejemplo |
| --- | --- | --- | --- |
| `GET` | `/api/proyecto` | Identifica el proyecto y al estudiante. | `SRMact1_t4 - Sandoval Reyes Miguel` |
| `GET` | `/api/materias` | Devuelve un listado de materias. | `["Programacion Web","Servicios Web","Conmutacion y Enrutamiento"]` |
| `GET` | `/api/total-materias` | Devuelve el número de materias registradas. | `3` |

Los endpoints se implementan con `@RestController` y `@GetMapping`. Spring Boot recibe las solicitudes HTTP y ejecuta automáticamente el método asociado a cada ruta.

## Requisitos

- JDK 17.
- Conexión a internet durante la primera ejecución para obtener las dependencias de Gradle.
- Terminal CMD o terminal integrada de Visual Studio Code.

## Ejecución local

Desde la carpeta raíz del proyecto, verificar Java y ejecutar la aplicación en Windows:

```cmd
java -version
gradlew.bat bootRun
```

La configuración utilizada en `src/main/resources/application.properties` es:

```properties
spring.application.name=SRMact1_t4
server.port=8082
```

Con la aplicación iniciada, los endpoints locales estarán disponibles en:

- http://localhost:8082/api/proyecto
- http://localhost:8082/api/materias
- http://localhost:8082/api/total-materias

Para detener la aplicación local, presionar `Ctrl + C` en la terminal.

## Compilación

Para generar el archivo ejecutable JAR en Windows:

```cmd
gradlew.bat bootJar
```

El archivo generado se encuentra en:

```text
build/libs/SRMact1_t4-0.0.1-SNAPSHOT.jar
```

## Despliegue en VPS

La aplicación se desplegó en un servidor Ubuntu con **OpenJDK 17**, utilizando el **puerto 8082**, independiente del proyecto frontend que también se encuentra alojado en el VPS.

### 1. Crear el directorio en el servidor

Conectarse mediante SSH y preparar la ubicación del JAR:

```bash
ssh root@167.99.15.140
mkdir -p /var/www/SRMact1_t4
```

### 2. Transferir el JAR

Desde CMD de Windows, en la carpeta del proyecto y fuera de la sesión SSH:

```cmd
scp build\libs\SRMact1_t4-0.0.1-SNAPSHOT.jar root@167.99.15.140:/var/www/SRMact1_t4/
```

### 3. Iniciar Spring Boot en el servidor

Dentro de la sesión SSH:

```bash
cd /var/www/SRMact1_t4
nohup java -jar SRMact1_t4-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
```

La opción `nohup` permite que la aplicación continúe ejecutándose después de cerrar la sesión SSH. La salida del proceso se guarda en `app.log`.

### 4. Verificar el funcionamiento

Desde el VPS:

```bash
curl http://localhost:8082/api/proyecto
curl http://localhost:8082/api/materias
curl http://localhost:8082/api/total-materias
```

### Endpoints publicados

Los tres endpoints fueron comprobados desde un navegador externo al VPS:

- **Proyecto:** http://167.99.15.140:8082/api/proyecto
- **Materias:** http://167.99.15.140:8082/api/materias
- **Total de materias:** http://167.99.15.140:8082/api/total-materias

## Conceptos utilizados

- **`@SpringBootApplication`:** identifica la clase principal y habilita la configuración automática de Spring Boot.
- **`@RestController`:** declara un controlador que devuelve directamente el cuerpo de la respuesta HTTP.
- **`@RequestMapping`:** establece una ruta base compartida por los métodos de un controlador.
- **`@GetMapping`:** relaciona una petición HTTP `GET` con un método Java y su endpoint.
- **Tomcat embebido:** permite ejecutar la aplicación web sin instalar un servidor de aplicaciones separado.

## Resultado

Se desarrolló una aplicación funcional con **dos controladores y tres endpoints GET**, probada localmente y desplegada en un VPS con Java 17 y puerto 8082. Las respuestas del proyecto, el listado de materias y su cantidad son accesibles a través de HTTP desde un navegador externo.
