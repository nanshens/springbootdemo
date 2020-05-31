package com.ns.springdatajpa.entity.many2many;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ns.springdatajpa.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author ns
 * @create 2020-05-29
 * 多对多关系维护端
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Student extends BaseEntity {
	private String name;

	/**
	 * 自定义关系维护表信息 默认策略 table: 维护表名_被维护表名, column: 维护表名_id, 被维护表名_id
	 *  @JoinTable(name = "student_course",
	 *  joinColumns = @JoinColumn(name = "student_id"),
	 * 	inverseJoinColumns = @JoinColumn(name = "course_id"))
	 */
	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(name = "student_course")
//	@JSONField(serialize = false)
	private List<Course> courseList;

	public Student(String name) {
		this.name = name;
	}
}
