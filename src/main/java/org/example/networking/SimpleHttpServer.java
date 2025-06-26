package org.example.networking;

import java.io.*;
import java.net.*;

public class SimpleHttpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(35000);
        System.out.println("Servidor web escuchando en puerto 35000...");
        while (true) {
            Socket clientSocket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            OutputStream out = clientSocket.getOutputStream();
            String line;
            while ((line = in.readLine()) != null && !line.isEmpty()) {
                System.out.println(line);
            }
            String response =
                    "HTTP/1.1 200 OK\r\n" +
                            "Content-Type: text/html\r\n\r\n" +
                            "<html><body><h1>¡Hola Mundo!</h1></body></html>";
            out.write(response.getBytes());
            out.flush();
            clientSocket.close();
        }
    }
}
