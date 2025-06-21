package org.example.multifilehttpserver;

import java.io.*;
import java.net.*;
import java.nio.file.*;

public class MultiFileHttpServerApplication {
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
            String path = parts[1].equals("/") ? "/index.html" : parts[1];

            // Ruta real de los archivos en resources/web
            String basePath = "src/main/resources/web";
            File file = new File(basePath + path);
            String contentType = guessContentType(path);

            if (file.exists() && !file.isDirectory()) {
                byte[] content = Files.readAllBytes(file.toPath());
                out.write(("HTTP/1.1 200 OK\r\nContent-Type: " + contentType + "\r\n\r\n").getBytes());
                out.write(content);
            } else {
                String notFound = "<html><body><h1>404 Not Found</h1></body></html>";
                out.write(("HTTP/1.1 404 Not Found\r\nContent-Type: text/html\r\n\r\n").getBytes());
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

