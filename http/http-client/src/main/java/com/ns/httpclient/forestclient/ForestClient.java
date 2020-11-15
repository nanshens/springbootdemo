package com.ns.httpclient.forestclient;

import com.dtflys.forest.annotation.Body;
import com.dtflys.forest.annotation.DataParam;
import com.dtflys.forest.annotation.DataVariable;
import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Header;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Query;
import com.dtflys.forest.annotation.Request;
import com.dtflys.forest.callback.OnError;
import com.dtflys.forest.callback.OnSuccess;
import com.ns.httpclient.dto.User;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author ns
 * @create 2020-08-26
 * 更多用法请看官方文档: https://dt_flys.gitee.io/forest
 */
@Component
public interface ForestClient {
    @Request(url = "http://localhost:54546/get")
    String get();

    @Request(url = "http://localhost:54546/getjson")
    String getjson(@DataParam("qry") String qryValue);

    @Request(url = "http://localhost:54546/getjson")
    String getjson1(@Query("qry") String qry);

    @Get(url = "http://localhost:54546/getjson")
    String getjson2(@Query Map<String, Object> map);

    @Request(
            url = "http://localhost:54546/getjson",
            headers = {
                    "Accept-Charset: utf-8",
                    "Content-Type: text/plain"
            }
    )
    String multipleHeaders();

    @Request(
            url = "http://localhost:54546/getjson",
            headers = {
                    "Accept-Charset: ${encoding}",
                    "Content-Type: text/plain"
            }
            )
    String bindingHeader(@DataVariable("encoding") String encoding);

    @Request(url = "http://localhost:54546/getjson")
    String postUser(@Header("Accept") String accept, @Header("accessToken") String accessToken);

    @Request(url = "http://localhost:${port}/hello/user?username=foo")
    String headHelloUser(@Header Map<String, Object> headerMap);

    @Request(url = "http://localhost:54546/get/${id}")
    String getOne(@DataVariable("id") String id, @DataParam("obj") String obj);


    @Post(
            url = "http://localhost:${port}/user",
            headers = {"Accept:text/plain"},
            contentType = "application/x-www-form-urlencoded"
    )
    String sendPost(@Body("username") String username, @Body("password") String password);

    @Request(
            url = "http://localhost:8080/text/data",
            dataType = "json"
    )

    Map getData();

    @Get(
            url = "http://localhost:8080/user?id=${0}",
            dataType = "json"
    )
    User getUser(Integer id);

    @Request(
            url = "http://localhost:5000/hello/user",
            headers = {"Accept:text/plain"},
            data = "username=${username}"
    )
    String send(@DataVariable("username") String username, OnSuccess<String> onSuccess, OnError onError);

}
