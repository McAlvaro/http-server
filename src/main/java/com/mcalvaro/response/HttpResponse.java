package com.mcalvaro.response;

public class HttpResponse {

    private String response;

    public HttpResponse(String response) {
        this.response = response;
    }

    public byte[] toBytes() {
        return response.getBytes();
    }
}
