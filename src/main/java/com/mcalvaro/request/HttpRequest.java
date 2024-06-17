package com.mcalvaro.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpRequest {

    private String method;

    private String path;

    public HttpRequest(String method, String path) {
        this.method = method;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }

    public static HttpRequest parse(InputStream inputStream) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line = reader.readLine();

        if (line == null || line.isEmpty()) {
            throw new IOException("Empty request");
        }

        String[] parts = line.split(" ");
        if (parts.length < 2) {
            throw new IOException("Invalid request line: " + line);
        }

        return new HttpRequest(parts[0], parts[1]);
    }
}
