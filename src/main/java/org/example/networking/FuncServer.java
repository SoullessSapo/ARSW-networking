package org.example.networking;

import java.io.*;
import java.net.*;

public class FuncServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(35002);
        System.out.println("Servidor Func listo en puerto 35002...");
        Socket clientSocket = serverSocket.accept();
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String mode = "cos";
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.startsWith("fun:")) {
                mode = inputLine.substring(4);
                out.println("Modo cambiado a: " + mode);
            } else {
                double value = Double.parseDouble(inputLine);
                double result;
                switch (mode) {
                    case "sin": result = Math.sin(value); break;
                    case "cos": result = Math.cos(value); break;
                    case "tan": result = Math.tan(value); break;
                    default: result = Double.NaN;
                }
                out.println("Resultado: " + result);
            }
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
