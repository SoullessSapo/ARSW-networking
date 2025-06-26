package org.example.networking;

import java.io.*;
import java.net.*;

public class SquareClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 35001);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        System.out.println("Ingrese un número para obtener el cuadrado:");
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
