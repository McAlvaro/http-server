package com.mcalvaro.response;

public class HttpResponse {

    private String headers;

    private byte[] body;

    public HttpResponse(String headers, byte[] body) {

        this.headers = headers;
        this.body = body;
    }

    public HttpResponse(byte[] fullResponse) {
        this.headers = new String(fullResponse);
        this.body = new byte[0];
    }

    public byte[] toBytes() {
        byte[] headerBytes = headers.getBytes();
        byte[] response = new byte[headerBytes.length + body.length];

        System.arraycopy(headerBytes, 0, response, 0, headerBytes.length);
        System.arraycopy(body, 0, response, headerBytes.length, body.length);

        return response;
    }
}
