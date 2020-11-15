package com.ns.httpclient;

import com.ns.httpclient.forestclient.ForestClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class HttpClientApplicationTests {

    @Autowired private ForestClient forestClient;
    @Test
    void contextLoads() {
        String get = forestClient.get();
        String getjson = forestClient.getjson("123");
        String getjson1 = forestClient.getjson1("123123");
        Map<String, Object> query = new HashMap<>();
        query.put("qry", "mapquery");
        String getjson2 = forestClient.getjson2(query);
        String getOne = forestClient.getOne("123", "obj");
        System.out.println();
    }

}
