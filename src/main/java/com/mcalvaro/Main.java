package com.mcalvaro;

import com.mcalvaro.service.HttpServer;

public class Main {
    public static void main(String[] args) {

        HttpServer server = HttpServer.getInstance();

        server.start(4221);

    }
}
