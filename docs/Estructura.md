#Modulos

com.tienda

├── auth
├── usuario
├── rol
├── categoria
├── producto
├── cliente
├── proveedor
├── compra
├── venta
├── inventario
│
├── config
├── security
├── exception
├── common
└── util


| Clase            | Responsabilidad                                  |
| ---------------- | ------------------------------------------------ |
| **Entity**       | Representar la tabla de la base de datos.        |
| **Repository**   | Acceder a la base de datos usando JPA.           |
| **DTO Request**  | Definir los datos que recibe la API.             |
| **DTO Response** | Definir los datos que devuelve la API.           |
| **Mapper**       | Convertir entre DTO y Entity.                    |
| **Service**      | Declarar las operaciones disponibles del módulo. |
| **ServiceImpl**  | Implementar la lógica de negocio.                |
| **Controller**   | Recibir peticiones HTTP y devolver respuestas.   |
