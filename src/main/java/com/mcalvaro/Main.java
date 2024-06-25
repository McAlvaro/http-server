package com.mcalvaro;

import com.mcalvaro.service.HttpServer;

public class Main {
    public static void main(String[] args) {

        String directory = null;

        if (args.length > 1 && args[0].equals("--directory")) {

            directory = args[1];

        } else {

            System.out.println("Usage: java -jar your_server.jar --directory <path>");
            System.exit(1);

        }

        HttpServer server = HttpServer.getInstance(directory);

        server.start(4221);

    }
}
