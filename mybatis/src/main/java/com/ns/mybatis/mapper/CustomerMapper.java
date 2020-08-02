package com.ns.mybatis.mapper;

import com.ns.mybatis.entity.Customer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ns
 * @create 2020-07-16
 */
@Repository
public interface CustomerMapper {

	@Insert("insert into public.customer(id, name, code)values(uuid_generate_v4(), #{name}, #{code})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	int insert(Customer customer);

	@Delete("delete from public.customer where id = #{id}")
	int delete(String id);

	@Update("update public.customer set name = #{name} where id = #{id}")
	int update(String id, String name);

	@Select("select * from public.customer where id = #{id}")
	Customer select(String id);

	@Select("select * from public.customer")
	List<Customer> selectAll();

	Customer findById(String id);
}
