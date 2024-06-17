package com.mcalvaro.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import com.mcalvaro.request.HttpRequest;
import com.mcalvaro.response.HttpResponse;
import com.mcalvaro.router.Router;

public class RequestHandler implements Runnable {

    private Socket clientSocket;

    private Router router;

    public RequestHandler(Socket clientSocket, Router router) {
        this.clientSocket = clientSocket;
        this.router = router;
    }

    @Override
    public void run() {
        try {

            InputStream inputStream = clientSocket.getInputStream();

            HttpRequest request = HttpRequest.parse(inputStream);

            HttpResponse response = router.route(request);

            clientSocket.getOutputStream().write(response.toBytes());
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
