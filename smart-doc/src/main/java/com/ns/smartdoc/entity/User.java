package com.ns.smartdoc.entity;

/**
 * @author ns
 * @create 2020-09-08
 */

/**
 * 用户表
 * @author <a href="http://github.com/athc">dujf</a>
 * @date 2018/8/31
 * @since JDK1.8
 */
public class User {

    /**
     * 姓名
     */
//    @NotNull//有此注解生成文档require = true
    private String name;

    /**
     * 电话
     */
    private String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}