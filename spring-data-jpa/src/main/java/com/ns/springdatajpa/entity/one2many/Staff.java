package com.ns.springdatajpa.entity.one2many;

import com.ns.springdatajpa.entity.base.BaseEntity;
import com.ns.springdatajpa.entity.many2many.Course;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author ns
 * @create 2020-05-29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Staff extends BaseEntity {
	private String name;

	@ManyToMany
	@JoinTable(name = "staff_position")
	private List<Position> positionList;
}
