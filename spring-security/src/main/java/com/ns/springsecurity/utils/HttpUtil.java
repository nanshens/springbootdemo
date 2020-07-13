package com.ns.springsecurity.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ns
 * @create 2020-07-02
 */

public class HttpUtil {
	public static Map<String, String> getDataFromHttpReq(HttpServletRequest request) throws IOException {
		BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		StringBuilder responseStrBuilder = new StringBuilder();
		String inputStr;
		while ((inputStr = streamReader.readLine()) != null) {
			responseStrBuilder.append(inputStr);
		}
		Map<String, String> params = JSON.parseObject(responseStrBuilder.toString(), Map.class);
		return params;
	}

	public static void sendResult(HttpServletResponse response, int status, String result) throws IOException{
		response.setStatus(status);
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.write(result);
//		writer.flush();
//		writer.close();
	}
}
