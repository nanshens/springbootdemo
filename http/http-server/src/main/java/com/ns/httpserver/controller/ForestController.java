package com.ns.httpserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ns
 * @create 2020-09-02
 */
@RestController
//@RequestMapping("/forest")
public class ForestController {

    @GetMapping("/get")
    public String get(){
        return "hello";
    }

    @GetMapping("/getjson")
    public JSON list(String qry) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        JSONObject object = new JSONObject();
        object.put("success", true);
        object.put("data", list);
        object.put("map", qry);
        return object;
    }

    @GetMapping("get/{id}")
    public String getOne(@PathVariable("id")String id, String obj){
        return "id" + obj;
    }

    @PostMapping(value = "save", produces = "application/json;charset=UTF-8")
    public String save(@RequestBody Object obj){
        return "";
    }

    @PostMapping(value = "addAttachment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addAttachment(@RequestParam("files[]") List<MultipartFile> files, @RequestParam("id") String id){
        return "";
    }
}
