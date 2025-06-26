package org.example.networking;


import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class SyncWebSocketClient {

    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        WebSocketClient client = new WebSocketClient(new URI("ws://localhost:8080/ws/chat")) {

            @Override
            public void onOpen(ServerHandshake handshakedata) {
                System.out.println("✅ Conectado. Puedes empezar a enviar prompts.");
                new Thread(() -> {
                    Scanner scanner = new Scanner(System.in);
                    while (true) {
                        try {
                            System.out.print("Tú: ");
                            String input = scanner.nextLine();
                            if ("exit".equalsIgnoreCase(input)) {
                                close();
                                break;
                            }

                            String persona = "asistente";
                            String json = String.format("{\"prompt\":\"%s\", \"persona\":\"%s\"}", input, persona);
                            latch = new CountDownLatch(1);
                            send(json);
                            latch.await();  // Esperar hasta que llegue la respuesta
                        } catch (InterruptedException e) {
                            System.err.println("❌ Error en la espera: " + e.getMessage());
                        }
                    }
                }).start();
            }

            @Override
            public void onMessage(String message) {
                System.out.println("🧠 Respuesta: " + message);
                latch.countDown(); // Liberar el hilo principal
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                System.out.println("🔌 Conexión cerrada: " + reason);
            }

            @Override
            public void onError(Exception ex) {
                System.err.println("❌ Error: " + ex.getMessage());
                latch.countDown(); // Evitar bloqueo si hay error
            }
        };

        client.connect();
    }
}
