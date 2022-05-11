package com.home.skydance.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@RestController
public class HttpRequestController {

    private final static Logger log = LoggerFactory.getLogger(HttpRequestController.class);
    private final String url = "http://127.0.0.1:9001/demo-service";

    @GetMapping("/demo-service")
    private List<String> getAllTweets() {
        try {
            Thread.sleep(4000L); // delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.asList("RestTemplate","WebClient","HttpClient");
    }

    @GetMapping("/resttemplate-blocking")
    public List<String> getResttemplateBlocking() {
        log.info("请求开始");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<String>> response = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<String>>(){});
        List<String> result = response.getBody();
        log.info("请求完成");
        return result;
    }


    @GetMapping(value = "/flux-non-blocking", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getFluxNonBlocking() {
        log.info("Flux请求开始!");
        Flux<String> flux = WebClient.create()
                .get()
                .uri(url)
                .retrieve()
                .bodyToFlux(String.class);
        log.info("Flux请求结束!");
        flux.subscribe(result -> log.info(result.toString()));
        log.info("Flux数据接收完成!");
        return flux;
    }


    @GetMapping(value = "/httpclient-blocking", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public String getHttpclientBlocking() {
        log.info("httpclient send请求开始");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("httpclient send请求结束");
        return response.body();
    }

    @GetMapping(value = "/httpclient-non-blocking", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public String getHttpclientAsyncNonBlocking() {
        try {
            log.info("httpclient sendAsync请求开始!");
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            CompletableFuture<String> result = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body);
            log.info("httpclient sendAsync请求结束!");
            String arr = result.get();
            log.info("httpclient sendAsync请求结束 打印请求结果：" + arr);
            return arr;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
