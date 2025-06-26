# Ejercicios de Networking en Java

Este proyecto contiene varios ejercicios prácticos sobre programación de redes en Java. Cada archivo aborda un concepto distinto: manejo de URLs, descarga de páginas, sockets TCP, funciones matemáticas cliente-servidor y servidor web de archivos estáticos.

## Estructura recomendada

```
src/
└── main/
    ├── java/
    │   └── org/
    │       └── example/
    │           └── networking/
    │               ├── URLProperties.java
    │               ├── SimpleBrowser.java
    │               ├── SquareServer.java
    │               ├── SquareClient.java
    │               ├── FuncServer.java
    │               ├── FuncClient.java
    │               └── MultiFileHttpServer.java
    └── resources/
        └── web/
            ├── index.html
            ├── main.js
            └── logo.png
```

---

## 1. URLProperties.java

**¿Qué hace?**  
Imprime en consola las distintas propiedades de una URL.

**¿Cómo se usa?**  
Compila y ejecuta:
```sh
java org.example.networking.URLProperties
```
**Ejemplo de salida:**
```
Protocol: http
Authority: www.google.com:80
Host: www.google.com
Port: 80
Path: /search
Query: q=java
File: /search?q=java
Ref: top
```

---

## 2. SimpleBrowser.java

**¿Qué hace?**  
Pide al usuario una URL, descarga su contenido y lo guarda en el archivo `resultado.html`.

**¿Cómo se usa?**  
Ejecuta el programa y escribe una URL cuando lo pida:
```sh
java org.example.networking.SimpleBrowser
URL: https://www.example.com
```
**Ejemplo de funcionamiento:**
- El archivo `resultado.html` contendrá la página descargada.
- Puedes abrirlo con tu navegador.

---

## 3. SquareServer.java

**¿Qué hace?**  
Servidor TCP que recibe un número y responde su cuadrado.

**¿Cómo se usa?**  
Ejecuta el servidor:
```sh
java org.example.networking.SquareServer
```
Verás:
```
Servidor listo en puerto 35001...
```

---

## 4. SquareClient.java

**¿Qué hace?**  
Cliente TCP que se conecta a SquareServer, manda un número y recibe su cuadrado.

**¿Cómo se usa?**  
Ejecuta el cliente:
```sh
java org.example.networking.SquareClient
```
Ejemplo en consola:
```
Ingrese un número para obtener el cuadrado:
5
Respuesta: 25
10
Respuesta: 100
```

---

## 5. FuncServer.java

**¿Qué hace?**  
Servidor TCP que calcula seno, coseno o tangente según el comando recibido. Cambia el modo con `fun:sin`, `fun:cos` o `fun:tan`.

**¿Cómo se usa?**  
Ejecuta el servidor:
```sh
java org.example.networking.FuncServer
```
Verás:
```
Servidor Func listo en puerto 35002...
```

---

## 6. FuncClient.java

**¿Qué hace?**  
Cliente TCP para FuncServer. Permite cambiar la función matemática o enviar números.

**¿Cómo se usa?**  
Ejecuta el cliente:
```sh
java org.example.networking.FuncClient
```
**Ejemplo de sesión:**
```
Conectado al servidor FuncServer. Ingrese números o comandos (fun:sin, fun:cos, fun:tan):
0
Respuesta: Resultado: 1.0
fun:sin
Respuesta: Modo cambiado a: sin
1.5708
Respuesta: Resultado: 0.9999999999932537
fun:tan
Respuesta: Modo cambiado a: tan
1
Respuesta: Resultado: 1.5574077246549023
hola
Respuesta: Envíe un número válido
```

---

## 7. MultiFileHttpServer.java

**¿Qué hace?**  
Servidor HTTP básico que sirve archivos estáticos (HTML, JS, imágenes) desde `src/main/resources/web/`.

**¿Cómo se usa?**  
Ejecuta el servidor:
```sh
java org.example.networking.MultiFileHttpServer
```
Verás:
```
Servidor HTTP escuchando en puerto 35000
```
**Prueba en el navegador:**
- [http://localhost:35000/](http://localhost:35000/) — carga `index.html`
- [http://localhost:35000/main.js](http://localhost:35000/main.js)
- [http://localhost:35000/KaydenFachero.png](http://localhost:35000/KaydenFachero.png)
- [http://localhost:35000/noexiste.html](http://localhost:35000/noexiste.html) — responde "404 Not Found"

---

## Archivos Web de ejemplo

- **index.html**  
  Página de inicio con un script JS y una imagen.
- **main.js**  
  Código JS que imprime un mensaje en consola.
- **logo.png**  
  Cualquier imagen PNG de tu preferencia.

---

## Notas importantes

- Los puertos por defecto son:
  - SquareServer/SquareClient: **35001**
  - FuncServer/FuncClient: **35002**
  - MultiFileHttpServer: **35000**
- Ejecuta primero el servidor antes del cliente para cada ejercicio.
- Verifica que tus archivos están en la ruta correcta.
- Usa una terminal desde la raíz del proyecto para compilar y ejecutar.

---

**Autor:**  
Esteban Valencia  
Curso de Networking - Escuela Colombiana de Ingeniería
