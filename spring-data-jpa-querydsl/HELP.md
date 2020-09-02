# Spring Data Jpa QueryDsl

## 简介
此项目为Spring Data Jpa QueryDsl Demo.
QueryDsl 使用面向对象思想, 构建复杂查询语句的一种sql增强库.
可与spring-data-jpa结合.

## 内容及常见问题
1. 搭建(src\test\java\com\ns\springdatajpaquerydsl\SpringDataJpaQuerydslApplicationTests.java)
2. 简单查询
3. 复杂查询
7. 实体映射
5. 连表查询
6. 特殊查询(聚合查询)
4. 子查询

## jar
``` xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>

<dependency>
    <groupId>com.querydsl</groupId>
    <artifactId>querydsl-jpa</artifactId>
</dependency>

<dependency>
    <groupId>com.querydsl</groupId>
    <artifactId>querydsl-apt</artifactId>
    <scope>provided</scope>
</dependency>
```