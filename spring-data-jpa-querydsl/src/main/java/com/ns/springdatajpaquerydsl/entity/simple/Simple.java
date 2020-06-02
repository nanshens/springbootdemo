package com.ns.springdatajpaquerydsl.entity.simple;


import com.ns.springdatajpaquerydsl.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author ns
 * @create 2020-05-29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Simple extends BaseEntity {
	private String code;


	/**
	 * 尽量使用java8新的时间日期api
	 */
	private LocalDate date;
	private LocalTime time;
	private LocalDateTime dateTime;

	private Boolean active;
	/**
	 * 此处为版本号, 是实现乐观锁的一种方法,
	 * 如果做操作时, 另一个人操作了数据,致使版本号改变, 这次操作将会失败,抛出异常.
	 * 如不需要加锁,请删除,可以提高吞吐量.
	 * 不需要显性set, 执行操作时他会自动+1
	 *
	 * 悲观锁一般是sql级别的, 在读写前就拿到锁实现, 在entitymanager 在createquery后可以setlockmode.
	 * 如不了解相关知识, 请补充以便加强理解与使用.
	 */
	@Version
	private Integer version;


	/**
	 * audit注解, @CreatedDate @LastModifiedDate @CreatedBy @LastModifiedBy
	 * 可以自动设置相关属性,不需要显性set
	 *
	 */
	@CreatedDate
	@Column(name = "create_date")
	private LocalDateTime createDate;
	@LastModifiedDate
	@Column(name = "modify_date")
	private LocalDateTime modifyDate;
	@CreatedBy
	@Column(name = "create_by")
	private String createBy;
	@LastModifiedBy
	@Column(name = "modify_by")
	private String modifyBy;


	/**
	 * 嵌入一个普通类, 如地址信息在多个实体中运用,但不作为实体创建, 使用AttributeOverrides可以复写属性名
	 */
	@Embedded
	private Address address;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name="address1", column = @Column(name="ship_to_address1")),
			@AttributeOverride(name="address2", column = @Column(name="ship_to_address2")),
			@AttributeOverride(name="address3", column = @Column(name="ship_to_address3")),
			@AttributeOverride(name="postalCode", column = @Column(name="ship_to_postal_code")),
			@AttributeOverride(name="city", column = @Column(name="ship_to_city")),
			@AttributeOverride(name="country", column = @Column(name="ship_to_country")),
			@AttributeOverride(name="province", column = @Column(name="ship_to_province")),
			@AttributeOverride(name="phoneNo", column = @Column(name="ship_to_phoneNo")),
			@AttributeOverride(name="email", column = @Column(name="ship_to_email"))
	})
	private Address shipToAddress;

	@Enumerated(EnumType.STRING)
	private Status status;


	/**
	 * 修改数据库类型,长度及其精度
	 */
	@Column(columnDefinition = "numeric(19,4)")
	private BigDecimal amount;
}
