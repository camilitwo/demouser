# Spring Boot API de registro de usuarios
####
Esta es una simple API para registrar usuarios. Fue construido con Spring Boot y incluye Swagger UI para la prueba y la documentación.

## Prerrequisitos
####
* Java 8 o superior
* Maven

## Documentación
####
La documentación de la API se encuentra en la ruta http://localhost:8080/v3/api-docs y http://localhost:8080/v3/api-docs.yaml

## Ejecutando la API
####
1. Clona el repositorio:
```
git clone https://github.com/camilitwo/demouser.git
```

2. Ejecuta la API usando Maven:

   mvn spring-boot:run

## Probando la API
####
Para probar la API, puedes utilizar Swagger UI, que está disponible en http://localhost:8080/swagger-ui.html. Desde allí, puedes ver una lista de puntos finales disponibles y probarlos.

## Puntos finales
####
```
* POST /usuarios/: Crea un nuevo usuario.
* GET /usuarios/: Obtiene una lista de todos los usuarios.
* GET /usuarios/phones: Obtiene una lista de todos los teléfonos.
```

## Ejemplo de solicitud
####
Aquí hay un ejemplo de solicitud POST al punto final /usuarios/ para crear un nuevo usuario:

```shell
curl -X 'POST' \
  'http://localhost:8080/usuarios/' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "name": "Camilo Gonzalez",
  "email": "camilo.igv@gmail.com",
  "password": "hunter2$$123.",
  "phones": [
    {
      "number": "98989898",
      "citycode": "1",
      "contrycode": "1"
    }
  ]
}'
```

## Ejemplo de respuesta
####
```json
{
   "mensaje": "Usuario creado con éxito",
   "data": {
      "id": "d62eacda-1430-4d65-b756-05746fc984fa",
      "created": "04/01/2023 12:36:36",
      "modified": "04/01/2023 12:36:36",
      "lastLogin": "04/01/2023 12:36:36",
      "active": true
   }
}
```