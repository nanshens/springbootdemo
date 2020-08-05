package com.ns.restrepositories.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class Course extends BaseEntity {
	@Column(unique = true)
	private String code;
	/**
	 * 通过column 设置一些列的属性: 列名, 长度, 精度, 唯一性, 非空属性等
	 */
	@Column(name = "course_credit")
	private Integer credit;

//	@JSONField(serialize = false)
	@ManyToMany(mappedBy = "courseList")
	private List<Student> studentList;

	public Course(String code, int credit) {
		this.code = code;
		this.credit = credit;
	}
}
