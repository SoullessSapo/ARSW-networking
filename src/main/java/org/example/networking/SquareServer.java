package org.example.networking;
import java.io.*;
import java.net.*;

public class SquareServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(35001);
        System.out.println("Servidor listo en puerto 35001...");
        Socket clientSocket = serverSocket.accept();
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            try {
                int num = Integer.parseInt(inputLine);
                int square = num * num;
                out.println(square);
            } catch (NumberFormatException e) {
                out.println("Envíe un número válido");
            }
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
