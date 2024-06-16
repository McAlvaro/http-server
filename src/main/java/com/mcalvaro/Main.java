package com.mcalvaro;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.mcalvaro.service.HttpServer;

public class Main {
    public static void main(String[] args) {

        HttpServer server = HttpServer.getInstance();

        server.start(4221);

    }
}
