package com.mcalvaro.handlers;

import java.io.IOException;
import java.net.Socket;

public class RequestHandler implements Runnable {

    private Socket clientSocket;

    private final String OK_RESPONSE = "HTTP/1.1 200 OK\r\n\r\n";

    public RequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {

            clientSocket.getOutputStream().write(OK_RESPONSE.getBytes());
            clientSocket.getOutputStream().flush();

        } catch (IOException e) {

            System.out.println("IOException: " + e.getMessage());

        } finally {

            if (clientSocket != null) {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.out.println("IOException on close: " + e.getMessage());
                }
            }
        }
    }

}
