package com.mcalvaro.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.mcalvaro.handlers.RequestHandler;
import com.mcalvaro.router.Router;

public class HttpServer {

    private static HttpServer instance;

    private ServerSocket serverSocket;

    private Router router;

    private HttpServer() {
        router = new Router();
    };

    public static synchronized HttpServer getInstance() {
        if (instance == null) {
            instance = new HttpServer();
        }

        return instance;
    }

    public void start(int port) {
        try {

            serverSocket = new ServerSocket(port);

            serverSocket.setReuseAddress(true);

            System.out.println("Server started on port " + port);

            while (true) {

                Socket clientSocket = serverSocket.accept();

                System.out.println("accepted new connection");

                RequestHandler requestHandler = new RequestHandler(clientSocket, router);
                new Thread(requestHandler).start();
            }

        } catch (IOException e) {

            System.out.println("IOException: " + e.getMessage());
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    System.out.println("IOException on close: " + e.getMessage());
                }
            }
        }
    }
}
