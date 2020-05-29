package com.ns.springdatajpa.entity.many2many;

import com.ns.springdatajpa.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * @author ns
 * @create 2020-05-29
 * 多对多关系被维护端
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Course extends BaseEntity {
	private String code;
	/**
	 * 通过column 设置一些列的属性: 列名, 长度, 精度, 唯一性, 非空属性等
	 */
	@Column(name = "course_credit")
	private Integer credit;

	@ManyToMany(mappedBy = "courseList")
	private List<Student> studentList;

}
