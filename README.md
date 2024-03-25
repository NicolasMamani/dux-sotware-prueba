# Dux-Sotware-Prueba - Nicolas Mamani
Este es un repositorio para la prueba técnica de dux software realizada en el sistema operativo Linux Mint.

<div style="text-align:center;">
  <img src="https://res.cloudinary.com/dttgwvnoe/image/upload/v1711340504/Iconos/logoduxazul_zbg5y9.png" alt="Dux Sofware" width="200" style="border-radius: 25%;" />
</div>

| Spring Boot | JWT | Swagger | Docker | H2 Database                                                                                              | Mockito      |
|-------------|-----|---------|--------|----------------------------------------------------------------------------------------------------------|--------------|
| ![Spring Boot](https://res.cloudinary.com/dttgwvnoe/image/upload/v1711340017/Iconos/uhunhonoxlwpkcxm5luh.webp) | ![JWT](https://res.cloudinary.com/dttgwvnoe/image/upload/v1711340096/Iconos/jwt-icon_r3jf8x.png) | ![Swagger](https://res.cloudinary.com/dttgwvnoe/image/upload/v1711340168/Iconos/swagger-icon_iato1w.png) | ![Docker](https://res.cloudinary.com/dttgwvnoe/image/upload/v1711340248/Iconos/Docker-Logo_bmbgwl.png) | ![H2](https://res.cloudinary.com/dttgwvnoe/image/upload/v1711340319/Iconos/h2-database-icon_kymrzm.webp) | ![Mockito](https://res.cloudinary.com/dttgwvnoe/image/upload/v1711395151/mockito-icon_xvj7pi.jpg) |

## Instrucciones
### Generar la imagen de Docker
Antes de generar la imagen de Docker, asegurate de que el proyecto este compilado y empaquetado.
Para hacer esto, ejecuta el siguiente comando en la raíz del proyecto para limpiar y construir el proyecto con maven:
```
mvn clean package
```
Una vez generado el archivo JAR de tu aplicación, puedes construir la imagen Docker utilizando el siguiente comando:
```
docker build -t app-duxsoftware-mamani:1.1 .
```
### Correr la imagen de Docker
Una vez que hayas generado la imagen Docker, puedes ejecutar el contenedor utilizando el siguiente comando:
```
docker run -p8080:8080 --name app-duxsoftware-mamani app-duxsoftware-mamani:1.1
```
Esto iniciará la aplicación y la hará accesible en `http://localhost:8080`.

### Swagger
Si nos dirigimos a `http://localhost:8080/swagger-ui/index.html` podemos realizar lo siguientes pasos para probar la API.

1. Dirigite a la parte de auth-controller, especificamente en el `POST /auth/login`, haz clic a la opción "Try it out" y ingresa el siguiente usuario
    ```
    {
      "username": "test",
      "password": "12345"
    }
    ```
    Luego presionar `Execute`, lo que nos devolvera un token. Ejemplo de un token generado por JWT:
    ```
    {
      "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNzExMzQxMDU1LCJleHAiOjE3MTEzNDI0OTV9.X1O79ye833whetiASNrd7pWJzm8W9DGDN4g6V7Hv3Cc"
    }
    ```
2. Dirigite a la parte superior de la página, especificamente arriba de equipo-controller. Ahí veras la opción "Authorize".
    Haz clic en ella e ingresa tu token.

   <p style="text-align: center;">
    <img src="https://res.cloudinary.com/dttgwvnoe/image/upload/v1711341197/Iconos/captura-3-swagger_bubgx6.png" alt="authorize" />
   </p>
3. Después de esto puedes probar completamente la API .

### Base de datos H2

Para realizar consultas en la base de datos en memoria H2, sigue estos pasos:

1. Abre tu navegador web y ve a `http://localhost:8080/h2-console`.

   ![Página de inicio de sesión de H2](https://res.cloudinary.com/dttgwvnoe/image/upload/v1711368644/Iconos/login-h2-database_h1zmgn.png)

2. En el campo "JDBC URL", ingresa `jdbc:h2:mem:equipos_futbol`.

3. En el campo "User Name", ingresa `sa`.

4. Deja el campo "Password" vacío.

5. Haz clic en "Connect".

   ![Interfaz de H2](https://res.cloudinary.com/dttgwvnoe/image/upload/v1711368654/Iconos/captura-h2-database_r7wsyf.png)

Ahora deberías estar conectado a la consola de H2 y listo para realizar tus consultas.
