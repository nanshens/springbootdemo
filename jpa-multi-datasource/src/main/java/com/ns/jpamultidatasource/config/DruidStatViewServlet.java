package com.ns.jpamultidatasource.config;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @author ns
 * @create 2020-08-26
 */

@WebServlet(urlPatterns = "/druid/*",
        initParams = {
                @WebInitParam(name = "loginUsername", value = "nanshens"),// 用户名
                @WebInitParam(name = "loginPassword", value = "ns123"),// 密码
                @WebInitParam(name = "resetEnable", value = "false")// 禁用HTML页面上的“Reset All”功能
        }
)
public class DruidStatViewServlet extends StatViewServlet {
    private static final long serialVersionUID = 1L;
}