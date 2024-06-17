package com.mcalvaro.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private String method;

    private String path;

    private Map<String, String> headers;

    public HttpRequest(String method, String path, Map<String, String> headers) {
        this.method = method;
        this.path = path;
        this.headers = headers;
    }

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }

    public String getHeader(String headerKey) {
        return headers.get(headerKey);
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

        String method = parts[0];
        String path = parts[1];
        Map<String,String> headers = readHaders(reader);

        return new HttpRequest(method, path, headers);
    }

    private static Map<String, String> readHaders(BufferedReader reader) throws IOException {

        Map<String, String> headers = new HashMap<>();

        String line = "";
        while((line = reader.readLine()) != null && !line.isEmpty()) {

            String[] headerParts = line.split(":", 2);

            if(headerParts.length == 2) {
                headers.put(headerParts[0].trim().toLowerCase(), headerParts[1].trim());
            }
        }

        return headers;
    }
}
