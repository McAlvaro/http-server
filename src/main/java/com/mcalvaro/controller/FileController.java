package com.mcalvaro.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import com.mcalvaro.request.HttpRequest;
import com.mcalvaro.response.HttpResponse;

public class FileController {

    private String rootDirectory;

    public FileController(String rootDirectory) {

        this.rootDirectory = rootDirectory;
    }

    public HttpResponse handleFileRequest(HttpRequest request) {

        String filePath = rootDirectory + request.getPath().substring("/files/".length());

        File file = new File(filePath);

        if (file.exists() && file.isFile()) {

            try (FileInputStream fis = new FileInputStream(file)) {

                byte[] fileData = Files.readAllBytes(file.toPath());

                String headers = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: application/octet-stream\r\n" +
                        "Content-Length: " + fileData.length + "\r\n\r\n";

                return new HttpResponse(headers, fileData);

            } catch (IOException ex) {

                System.out.println("IOException: " + ex.getMessage());

                return new HttpResponse("HTTP/1.1 500 Internal Server Error\r\n\r\n".getBytes());
            }
        } else {
            return new HttpResponse("HTTP/1.1 404 Not Found\r\n\r\n".getBytes());
        }
    }
}
