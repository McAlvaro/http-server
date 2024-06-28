package com.mcalvaro.controller;

import com.mcalvaro.request.HttpRequest;
import com.mcalvaro.response.HttpResponse;

public interface Controller {

    HttpResponse handleRequest(HttpRequest request);
}
