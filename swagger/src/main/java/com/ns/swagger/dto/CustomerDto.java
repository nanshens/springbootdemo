package com.ns.swagger.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ns
 * @create 2020-06-11
 */
@Data
@ApiModel(description = "客户Dto")
public class CustomerDto {
	@ApiModelProperty(value = "编号")
	private String code;
	private String name;
}
