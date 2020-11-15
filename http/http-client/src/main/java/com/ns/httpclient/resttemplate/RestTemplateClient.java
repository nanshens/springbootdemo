package com.ns.httpclient.resttemplate;

import com.ns.httpclient.dto.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ns
 * @create 2020-09-07
 */
@Component
public class RestTemplateClient {
    public void method() {

        RestTemplate restTemplate = new RestTemplate();
        User notice = restTemplate.getForObject("http://xxx.top/notice/list/1/5"
                , User.class);

        Map<String,String> map = new HashMap<>();
        map.put("start","1");
        map.put("page","5");
        User user = restTemplate.getForObject("http://fantj.top/notice/list/"
                , User.class,map);
    }

    public void method1() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> entity = restTemplate.getForEntity("http://fantj.top/notice/list/1/5"
                , User.class);

        HttpStatus statusCode = entity.getStatusCode();
        System.out.println("statusCode.is2xxSuccessful()"+statusCode.is2xxSuccessful());

        User body = entity.getBody();
        System.out.println("entity.getBody()"+body);


        ResponseEntity.BodyBuilder status = ResponseEntity.status(statusCode);
        status.contentLength(100);
        status.body("我在这里添加一句话");
        ResponseEntity<Class<User>> body1 = status.body(User.class);
        Class<User> body2 = body1.getBody();
        System.out.println("body1.toString()"+body1.toString());
    }

    public void method2() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://47.xxx.xxx.96/register/checkEmail";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("email", "844072586@qq.com");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
        System.out.println(response.getBody());
    }
}
