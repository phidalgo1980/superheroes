# Superheroes

En base al enunciado del api propuesta, se expone una posible solución detallando su instalación y uso.

> **Aclaración**: esta solución se desarrolló en OpenJDK11 y Springboot 2.4.8 con Intellij como IDE y Linux Mint 19.1 Tessa como S.O.

Luego de bajarse el código ejecutar el script hecho en bash que realizará la compilación con Maven y el build de la imagen Docker.

`$ ./start.sh` 

Acceder con un browser a la siguiente dirección http://localhost:8080/swagger-ui.html

![alt text](https://github.com/phidalgo1980/superheroes/blob/main/images/superheroes_image_01.png?raw=true)

Para utilizar el api será necesario realizar una autenticación.
Por ejemplo:

```sh
{
  "password": "supersecret",
  "username": "admin"
}
```
Como se muestra en la siguiente imagen

![alt text](https://github.com/phidalgo1980/superheroes/blob/main/images/superheroes_image_02.png?raw=true)

Si la autenticación es exitosa se obtendrá un token en JWT que deberá ser usado en el Header de Authorization para consumir los distintos enpoints del api.
A continuación se muestra una posible respuesta exitosa con el token

![alt text](https://github.com/phidalgo1980/superheroes/blob/main/images/superheroes_image_03.png?raw=true)

Si se desean consumir los servicios desde el swagger es necesario agregar el token mediante el botón de Authorize que presenta el swagger. 
Una vez abierto el cuadro de diálogo copiar el token anteponiendo `Bearer`.

![alt text](https://github.com/phidalgo1980/superheroes/blob/main/images/superheroes_image_04.png?raw=true)

Podrá observarse que los servicios están con un ícono de candado cerrado, esto ya indica que los servicios serán utilizados de forma segura con el token cargado.

![alt text](https://github.com/phidalgo1980/superheroes/blob/main/images/superheroes_image_05.png?raw=true)

Ejemplo de la invocación del servicio de búsqueda de superheroe por Id

![alt text](https://github.com/phidalgo1980/superheroes/blob/main/images/superheroes_image_06.png?raw=true)


