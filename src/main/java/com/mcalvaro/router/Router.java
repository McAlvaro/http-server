package com.mcalvaro.router;

import java.util.HashMap;
import java.util.Map;

import com.mcalvaro.request.HttpRequest;
import com.mcalvaro.response.HttpResponse;

public class Router {

    private Map<String, String> routes;

    public Router() {
        routes = new HashMap<>();
        routes.put("/", "HTTP/1.1 200 OK\r\n\r\nWelcome to the home page!");
        routes.put("/contact", "HTTP/1.1 200 OK\r\n\r\nThis is the about page.");
    }

    public HttpResponse route(HttpRequest request) {
        String responseString = routes.getOrDefault(request.getPath(), "HTTP/1.1 404 Not Found\r\n\r\nPage not found.");

        return new HttpResponse(responseString);
    }
}
