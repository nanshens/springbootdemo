package com.ns.httpclient.okhttp3;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author ns
 * @create 2020-09-07
 */
@Component
public class OkHttp3Client {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public void method() throws IOException {
        OkHttpClient client = new OkHttpClient();
        OkHttpClient clientWith30sTimeout = client.newBuilder()
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        OkHttpClient client1  = client.newBuilder().build();

        Request request = new Request.Builder()
                .url("123123123123/123123//123123")
                .build();

        Response response = client.newCall(request).execute();
        String return1 = response.body().string();
    }

    public void method2() throws IOException {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, "json");
        Request request = new Request.Builder()
                .url("123123123123/123123//123123")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        String return2 =  response.body().string();
    }
}
