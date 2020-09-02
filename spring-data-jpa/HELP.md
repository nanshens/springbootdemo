# Spring Data Jpa

## 简介
此项目为Spring Data Jpa Demo. 当使用jpa的时候, 请抛弃mybatis的设计思想,从db first转换成model first,以便更好的理解及设计使用.
如没有orm使用经验, 请补充jpa规范或hibernate相关知识.

## 内容及常见问题
1. 常用注解及其实体代码编写(/src/main/java/com/ns/springdatajpa/entity)
2. 实体关系设计与展示. 好的实体设计会使系统更加简洁与稳定, 请仔细考虑后设计.(/src/main/java/com/ns/springdatajpa/entity)
3. 增删改查的级联设置与调试.(src/test/java/com/ns/springdatajpa/Many2ManyTests)
4. 循环引用的序列化问题(本例使用fastjson, 如有gson或jackjson, 请查找相关实现, 解决思路是一致的)(src/test/java/com/ns/springdatajpa/Many2ManyTests)
5. 特殊类型的设计与代码编写(/src/main/java/com/ns/springdatajpa/entity/Simple)
6. repository类的编写规则及其使用(/src/main/java/com/ns/springdatajpa/repo)
7. Specification, 复杂查询的使用(src/test/java/com/ns/springdatajpa/Many2OneTests)
8. n+1问题(/src/main/java/com.ns.springdatajpa.entity.many2one/SalesOrder, com.ns.springdatajpa.repo.many2one/SalesOrderRepo)
9. 自定义sql查询的实体映射问题(com.ns.springdatajpa.EntityMapper, src/test/java/com/ns/springdatajpa/Many2OneTests)

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
```