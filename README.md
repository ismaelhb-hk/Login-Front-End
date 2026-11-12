# Login-Back-End
Repository for back-end resources for a sign in and sign up Application.
Aplicación de escritorio cliente-servidor desarrollada para la gestión de usuarios, registro, autenticación y modificación de contraseñas. Este proyecto ha sido realizado para el módulo de Desarrollo de Interfaces (DI) del Ciclo Formativo de Grado Superior en Desarrollo de Aplicaciones Multiplataforma (DAM).

Información del Proyecto

Centro Educativo: IES San Juan de la Cruz

Ciclo Formativo: CFGS Desarrollo de Aplicaciones Multiplataforma (DAM)

Módulo: Desarrollo de Interfaces (DI)

Fecha Límite de Entrega: 13 de Noviembre de 2025 - 16:00h

Repositorios de Código Fuente

El proyecto se encuentra dividido en dos repositorios independientes:

Repositorio Front-End (Cliente JavaFX): https://github.com/ismaelhb-hk/Login-Front-End.git

Repositorio Back-End (Servicio RESTful): https://github.com/abdel-bell/Login-Back-End.git

Equipo de Desarrollo y Asignación de Tareas

El desarrollo del proyecto ha sido llevado a cabo por un equipo de tres personas. Las funcionalidades se han dividido en tres paquetes de trabajo 

-Abdel               Change Password: Diseño y lógica de la ventana de cambio de contraseña, 
                     verificación de credenciales actuales y actualización de los datos del usuario.   

-Edwin Jamil        Sign Up: Diseño de la interfaz de registro de nuevos usuarios, capturación de                                datos, validación de formularios y envío de información al servidor.

Ismael Hmaimou      Sign In: Diseño de la ventana de inicio de sesión, autenticación de usuarios,                                gestión  de la ventana principal y control del flujo de navegación.
                    MainWindows : Diseño de la ventana principal 




                    



Estructura y Arquitectura del Sistema

El sistema sigue un modelo distribuido en capas para separar la interfaz gráfica de la gestión de datos:

Cliente (Front-End):

Aplicación de escritorio desarrollada en JavaFX.

Interfaces de usuario diseñadas con la herramienta SceneBuilder y estructuradas en archivos FXML.

Separación clara entre la vista gráfica, el controlador y la capa de servicios mediante interfaces de lógica.

Servidor (Back-End):

Servicio web RESTful desplegado en el servidor de aplicaciones Glassfish 8.

Gestión de peticiones utilizando formatos JSON y XML para el intercambio de información.

Base de Datos:

Servidor de base de datos relacional MySQL 8.

Esquema de base de datos denominado signdb.

Conexión gestionada mediante un pool de conexiones en Glassfish con la referencia jdbc/__mysql.

Funcionalidades Desarrolladas

1. Registro de Usuario (Sign Up - WP1)

Formulario para la recogida de datos personales del usuario.

Comprobación del formato de los campos (dirección de correo electrónico, código postal, teléfono, campos requeridos).

Presentación de mensajes informativos y de error según la respuesta del servidor.

2. Autenticación de Usuario (Sign In - WP2)

Pantalla de acceso mediante correo electrónico y contraseña.

Redirección a la pantalla principal tras una autenticación correcta.

Control de la navegación general de la aplicación.

3. Cambio de Contraseña (Change Password - WP3)

Formulario para la modificación de la clave de acceso.
Comprobación de coincidencias y requerimientos de seguridad antes del envío.
Actualización de la información en el servidor.


Requisitos de Instalación y Despliegue

Configuración del Servidor y Base de Datos
Iniciar la base de datos en MySQL 8.
Crear la base de datos signdb y cargar los datos iniciales mediante el script SQL proporcionado (bankdb.sql).
En el servidor Glassfish 8, configurar un recurso JDBC con el nombre exacto jdbc/__mysql vinculado al pool de conexiones de la base de datos MySQL.
Desplegar la aplicación web del Back-End en Glassfish 8.

Ejecución del Cliente

Clonar el repositorio del Front-End.
Abrir el proyecto en el entorno de desarrollo (IDE) habitual.
Asegurar la correcta vinculación de las librerías de JavaFX.
Ejecutar la clase principal de la aplicación.


Pruebas de Software (RA8)

Pruebas unitarias e integradas de interfaz: No desarrollado (Sección omitida debido a la reorganización de tiempos y alcance lectivo acordado durante el desarrollo del reto).



