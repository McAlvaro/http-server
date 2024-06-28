package com.mcalvaro.router.config;

import java.util.Map;

public class RoutesConfig {

    private Map<String, Route> routes;

    public Map<String, Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Map<String, Route> routes) {
        this.routes = routes;
    }
}
