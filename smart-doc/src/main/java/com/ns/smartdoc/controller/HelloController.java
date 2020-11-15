package com.ns.smartdoc.controller;

/**
 * @author ns
 * @create 2020-09-08
 */

import com.ns.smartdoc.entity.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * hello
 *
 * @author <a href="http://github.com/athc">dujf</a>
 * @date 2018/8/31
 * @since JDK1.8
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    /**
     * 创建
     *
     * @param user
     * @return
     */
    @PostMapping
    public User create(User user) {
        return user;
    }

    /**
     * 查询
     *
     * @param name 用户名
     * @return
     */
    @GetMapping
    public User getName(String name) {
        return new User();
    }

    /**
     * 修改
     *
     * @param name
     * @return
     */
    @PutMapping
    public String update(String name) {
        return name;
    }

    /**
     * 删除
     *
     * @param name
     * @return
     */
    @DeleteMapping
    public String delete(String name) {
        return name;
    }
}