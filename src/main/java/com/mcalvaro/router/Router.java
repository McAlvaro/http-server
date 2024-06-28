package com.mcalvaro.router;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import com.mcalvaro.controller.Controller;
import com.mcalvaro.controller.FileController;
import com.mcalvaro.request.HttpRequest;
import com.mcalvaro.response.HttpResponse;
import com.mcalvaro.router.config.Route;
import com.mcalvaro.router.config.RoutesConfig;

public class Router {

    private Map<String, Controller> dynamicRoutes;

    private Map<String, String> staticRoutes;

    public Router(String rootDirectory) {

        dynamicRoutes = new HashMap<>();
        staticRoutes = new HashMap<>();

        // TODO: Load routes from file
        dynamicRoutes.put("/files/", new FileController(rootDirectory));

        loadStaticRoutesFromYaml("/routes.yml");
    }

    private void loadStaticRoutesFromYaml(String yamlFilePath) {
        Yaml yaml = new Yaml(new Constructor(RoutesConfig.class));

        try (InputStream input = getClass().getResourceAsStream(yamlFilePath)) {

            if (input == null) {
                System.out.println("Sorry, unable to find " + yamlFilePath);

                return;
            }

            RoutesConfig config = yaml.load(input);

            for (Map.Entry<String, Route> entry : config.getRoutes().entrySet()) {
                staticRoutes.put(entry.getKey(), entry.getValue().getResponse());
            }
        } catch (IOException ex) {

            ex.printStackTrace();
        }
    }

    public HttpResponse route(HttpRequest request) {

        for (Map.Entry<String, Controller> entry : dynamicRoutes.entrySet()) {
            if (request.getPath().startsWith(entry.getKey())) {
                return entry.getValue().handleRequest(request);
            }
        }

        String responseString = staticRoutes.getOrDefault(request.getPath(),
                "HTTP/1.1 404 Not Found\r\n\r\nPage not found.");

        return new HttpResponse(responseString.getBytes());
    }
}
