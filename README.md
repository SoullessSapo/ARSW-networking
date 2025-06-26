
# Ejercicio 4.5.1 - Servidor Web en Java (MultiFileHttpServer)

Este ejercicio consiste en la implementación de un servidor web sencillo en Java capaz de recibir múltiples solicitudes de manera secuencial (no concurrente), y de servir archivos estáticos de diferentes tipos como páginas HTML, archivos JavaScript e imágenes (PNG, JPEG, GIF).

## Objetivo

El servidor debe ser capaz de:
- Escuchar peticiones HTTP en un puerto configurable (por defecto, `35000`).
- Responder a las solicitudes enviando archivos estáticos (HTML, JS, imágenes) ubicados en un directorio específico.
- Enviar la cabecera HTTP con el **Content-Type** apropiado según la extensión del archivo solicitado.
- Responder con un mensaje de error `404 Not Found` si el recurso no existe.

## Estructura recomendada

```
src/
├── main/
│   ├── java/
│   │   └── org/
│   │       └── example/
│   │           └── networking/
│   │               └── MultiFileHttpServer.java
│   └── resources/
│       └── web/
│           ├── index.html
│           ├── main.js
│           └── logo.png
```

## ¿Cómo funciona?

- El servidor escucha en el puerto 35000.
- Al recibir una solicitud HTTP (por ejemplo, `GET /index.html`), busca el archivo solicitado en la carpeta `src/main/resources/web`.
- Detecta el tipo MIME del archivo y lo retorna correctamente al navegador o cliente.
- Si el archivo no existe, responde con un error `404 Not Found`.

## Ejecución

1. **Coloca los archivos estáticos** (HTML, JS, imágenes) en la carpeta:  
   `src/main/resources/web/`

2. **Ejecuta la clase** `MultiFileHttpServer` desde tu IDE o consola.

3. **Accede desde el navegador**:
    - [http://localhost:35000/](http://localhost:35000/)
    - [http://localhost:35000/main.js](http://localhost:35000/main.js)
    - [http://localhost:35000/logo.png](http://localhost:35000/logo.png)

## Ejemplo de archivos estáticos

`index.html`
```html
<!DOCTYPE html>
<html>
<head>
    <title>Servidor Web Java</title>
    <script src="main.js"></script>
</head>
<body>
    <h1>Hola desde Java Web Server</h1>
    <img src="logo.png" alt="Logo" width="200">
</body>
</html>
```

`main.js`
```js
console.log("¡JS cargado correctamente desde el servidor Java!");
```

Coloca cualquier imagen `.png` como `logo.png`.

## Código del servidor

La clase principal es:

```java
package org.example.networking;

import java.io.*;
import java.net.*;
import java.nio.file.*;

public class MultiFileHttpServer {
   public static void main(String[] args) throws IOException {
      int port = 35000;
      ServerSocket serverSocket = new ServerSocket(port);
      System.out.println("Servidor HTTP escuchando en puerto " + port);

      while (true) {
         Socket clientSocket = serverSocket.accept();
         BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
         OutputStream out = clientSocket.getOutputStream();

         String requestLine = in.readLine();
         if (requestLine == null) continue;
         System.out.println("Request: " + requestLine);
         String[] parts = requestLine.split(" ");
         if (parts.length < 2) continue;
         String path = parts[1].equals("/") ? "/web/index.html" : parts[1];

         String basePath = "src/main/resources/web";
         File file = new File(basePath + path);
         String contentType = guessContentType(path);

         if (file.exists() && !file.isDirectory()) {
            byte[] content = Files.readAllBytes(file.toPath());
            out.write(("HTTP/1.1 200 OK
                    Content - Type:" + contentType + "

            ").getBytes());
            out.write(content);
         } else {
            String notFound = "<html><body><h1>404 Not Found</h1></body></html>";
            out.write(("HTTP/1.1 404 Not Found
                    Content - Type:text / html

            ").getBytes());
            out.write(notFound.getBytes());
         }
         out.flush();
         clientSocket.close();
      }
   }

   private static String guessContentType(String path) {
      if (path.endsWith(".html")) return "text/html";
      if (path.endsWith(".js")) return "application/javascript";
      if (path.endsWith(".png")) return "image/png";
      if (path.endsWith(".jpg") || path.endsWith(".jpeg")) return "image/jpeg";
      if (path.endsWith(".gif")) return "image/gif";
      return "application/octet-stream";
   }
}
```

---

## Observaciones

- El servidor **no es concurrente** (atiende una petición a la vez).
- Para producción real se recomienda usar servidores web profesionales.
- Este ejercicio es únicamente educativo y cumple con el requerimiento del taller de networking.

---

**Autor:**  
Esteban Valencia  
Curso de Networking - Escuela Colombiana de Ingeniería
