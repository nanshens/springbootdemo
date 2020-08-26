# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.3.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.3.RELEASE/maven-plugin/reference/html/#build-image)


三种整合方式
1. 使用sharding-jdbc-spring-boot-starter整合
这种方式比较简单只要加入sharding-jdbc-spring-boot-starter依赖，在application.yml中配置数据源，分片策略即可使用，这种方式简单，方便但是不够灵活

2. 编码方式
这种方式使用Java config的方式，数据源，分片策略都要编码，这种方式比较灵活，但是所有策略都要硬编码，不方便维护

3.数据源分片策略使用单独yml文件配置，通过Java config方式定义数据源
推荐

demo场景:
有客户表, 订单表, 订单项目表, 项目表. 关系如下,订单表存储客户信息, 订单项目表存储订单信息和项目信息
分表策略:
客户表和项目表不分表, 订单表和订单项目表分表

