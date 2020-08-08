package com.ns.mybatismybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author ns
 * @create 2020-07-15
 */
@Data
public class Customer {
	@TableId(type = IdType.ASSIGN_UUID)
	private String id;
	private String name;
	private String code;
	private String phone;
}
