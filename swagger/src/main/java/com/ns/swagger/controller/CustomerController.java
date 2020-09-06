package com.ns.swagger.controller;

import com.ns.swagger.dto.CustomerDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ns
 * @create 2020-06-10
 */
@RestController
@RequestMapping("/customer")
@Api(tags = "CustomerController")
public class CustomerController {
	@ApiOperation(value = "接口的功能介绍",notes = "提示接口使用者注意事项",httpMethod = "GET")
	@ApiImplicitParam(dataType = "string",name = "name",value = "姓名",required = true)
	@GetMapping(value = "/get")
	public String get(String name) {
		return "hello "+ name;
	}

	@ApiOperation(value = "接口的功能介绍",notes = "提示接口使用者注意事项",httpMethod = "POST")
	@ApiImplicitParams({@ApiImplicitParam(dataType = "CustomerDto",name = "dto", value = "客户信息",required = true)})
	@ApiResponses({@ApiResponse(code=400,message="请求参数没填好"),@ApiResponse(code=404,message="请求路径没有")})
	@PostMapping(value = "/save")
	public String save(@RequestBody CustomerDto dto) {
		return "hello ";
	}
}
