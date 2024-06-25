package com.mcalvaro.router;

import java.util.HashMap;
import java.util.Map;

import com.mcalvaro.controller.FileController;
import com.mcalvaro.request.HttpRequest;
import com.mcalvaro.response.HttpResponse;

public class Router {

    private Map<String, String> routes;

    private FileController fileController;

    public Router(String rootDirectory) {

        routes = new HashMap<>();
        routes.put("/", "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n\r\nWelcome to the home page!");
        routes.put("/contact", "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n\r\nThis is the Contact page.");

        this.fileController = new FileController(rootDirectory);
    }

    public HttpResponse route(HttpRequest request) {

        if (request.getPath().startsWith("/files/")) {
            return fileController.handleFileRequest(request);
        }

        String responseString = routes.getOrDefault(request.getPath(), "HTTP/1.1 404 Not Found\r\n\r\nPage not found.");

        return new HttpResponse(responseString.getBytes());
    }
}
