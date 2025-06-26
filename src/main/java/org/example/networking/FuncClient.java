package org.example.networking;

import java.io.*;
import java.net.*;

public class FuncClient {
    public static void main(String[] args) throws IOException {
        // Conéctate al servidor que corre en localhost y puerto 35002 (ajusta si es necesario)
        Socket socket = new Socket("127.0.0.1", 35002);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Conectado al servidor FuncServer. Ingrese números o comandos (fun:sin, fun:cos, fun:tan):");
        String userInput;
        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            System.out.println("Respuesta: " + in.readLine());
        }

        out.close();
        in.close();
        stdIn.close();
        socket.close();
    }
}

