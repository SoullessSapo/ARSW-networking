package org.example.networking;

import java.io.*;
import java.net.*;

public class SimpleBrowser {
    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("URL: ");
        String urlString = input.readLine();

        URI uri = new URI(urlString);
        URL url = uri.toURL();
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        PrintWriter writer = new PrintWriter(new FileWriter("resultado.html"));

        String line;
        while ((line = reader.readLine()) != null) {
            writer.println(line);
        }

        reader.close();
        writer.close();
        System.out.println("Página guardada en resultado.html");
    }
}
