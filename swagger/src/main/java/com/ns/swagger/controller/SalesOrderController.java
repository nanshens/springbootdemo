package com.ns.swagger.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ns
 * @create 2020-06-10
 */
@RestController
@RequestMapping("/salesorder")
public class SalesOrderController {
	@ApiOperation(value = "接口的功能介绍",notes = "提示接口使用者注意事项",httpMethod = "GET")
	@ApiImplicitParam(dataType = "string",name = "name",value = "姓名",required = true)
	@GetMapping(value = "/123")
	public String index(String name) {
		return "hello "+ name;
	}
}
