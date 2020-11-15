package com.ns.httpclient.jdk11;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * @author ns
 * @create 2020-09-07
 */
@Component
public class JdkClient {

    public void method() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(5))
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();

        HttpRequest getRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://www.flydean.com"))
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.122 Safari/537.36")
                .build();

        HttpRequest.BodyPublisher requestBody = HttpRequest.BodyPublishers
                .ofString("{ 我是body }");
        HttpRequest postRequest = HttpRequest.newBuilder()
                .POST(requestBody)
                .uri(URI.create("http://www.flydean.com"))
                .build();

        HttpResponse<String> response = client.send( getRequest, HttpResponse.BodyHandlers.ofString());
        String respnseBody = response.body();
    }
}
