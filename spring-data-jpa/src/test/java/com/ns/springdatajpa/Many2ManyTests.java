package com.ns.springdatajpa;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.ns.springdatajpa.entity.many2many.Course;
import com.ns.springdatajpa.entity.many2many.Student;
import com.ns.springdatajpa.repo.many2many.CourseRepo;
import com.ns.springdatajpa.repo.many2many.StudentRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional(rollbackOn = Exception.class)
@Rollback(false)
class Many2ManyTests {
	@Autowired private CourseRepo courseRepo;
	@Autowired private StudentRepo studentRepo;

//	@Test
//	void contextLoads() {
//	}

	/**
	 * 添加被维护端数据
	 */
	@Test
	void addCourse() {
		Course course = new Course();
		course.setCode("1");
		course.setCredit(0);
		/*
		* 被维护端不能更新中间表数据
		* */
//		course.setStudentList(studentRepo.findAll());
		courseRepo.save(course);
	}

	/**
	 * 添加维护端数据
	 */
	@Test
	void addStudent() {
		Student student = new Student("xiaoming");
		/*
		* 添加被维护端数据, 更新到中间表
		*
		* */
		student.setCourseList(courseRepo.findAll());
		studentRepo.save(student);
	}

	/** 问题4
	 * 查询被维护端数据, 因为实体循环引用, 序列化会引起stackoverflow,
	 * 选择自定义序列化方式, 过滤掉循环引用的属性.
	 * 也可以在entity上用注解配置,灵活性不大,请自行选择.
	 * SerializerFeature.DisableCircularReferenceDetect 为去掉循环引用检测,
	 * fastjson会对重复引用进行$ref处理, 去掉即可显示完整数据, 请注意过滤必要属性, 以免引起stackoverflow异常.
	 */
	@Test
	void getCourse() {
		List<Course> courses = courseRepo.findAll();

		SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
		filter.getExcludes().add("courseList");
		System.out.println(JSONObject.toJSONString(courses, filter, SerializerFeature.DisableCircularReferenceDetect));
	}

	@Test
	void getStudent() {
		List<Student> students = studentRepo.findAll();

		SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
		filter.getExcludes().add("studentList");
		System.out.println(JSONObject.toJSONString(students, filter, SerializerFeature.DisableCircularReferenceDetect));
	}


	/**
	 * 被维护端数据更新, 正常更新数据.
	 */
	@Test
	void updateCourse() {
	}

	/** 问题3
	 * 维护端数据更新,
	 * 维护端级联类型
	 * 不填: 更新可以更新到被维护端数据, 不能新建被维护端数据(抛异常), 删除只是删除中间表信息, 不会删除被维护端数据
	 * PERSIST: 更新可以更新到被维护端数据, 可以新建维护端数据(但只有id, 也有中间表数据), 删除只是删除中间表信息, 不会删除被维护端数据
	 * MERGE: 更新可以更新到被维护端数据, 可以新建维护端数据, 删除只是删除中间表信息, 不会删除被维护端数据
	 * REMOVE: 更新可以更新到被维护端数据, 不能新建被维护端数据(抛异常),删除中间表信息, 删除被维护端数据
	 * DETACH: 更新可以更新到被维护端数据, 不能新建被维护端数据(抛异常),删除被引用的数据及其中间表
	 * REFRESH: 多人同时操作时,可能拿到脏数据, 在执行操作前先获取最新数据
	 * ALL: 包括以上所有操作(禁止使用)
	 * PERSIST和MERGE不同时使用
	 */
	@Test
	void updateStudent() {
		Student student = studentRepo.findByName("xiaoming");
		List<Course> courseList = student.getCourseList();

		//更新
		courseList.get(0).setCredit(105);
		student.setCourseList(courseList);

		//新建
		courseList.add(new Course("5", 100));
		student.setCourseList(courseList);

		//删除
		courseList.clear();
		student.setCourseList(courseList);

		studentRepo.save(student);
	}


}
