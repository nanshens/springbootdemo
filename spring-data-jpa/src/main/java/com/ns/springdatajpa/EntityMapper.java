package com.ns.springdatajpa;

import com.ns.springdatajpa.vo.Tag;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ns
 * @create 2020-05-31
 */

public class EntityMapper {

	/**
	 * 通过有参构造函数映射
	 *
	 * */
	public static <T> List<T> mapping(List<Object[]> list, Class<T> clazz, int tag) {
		if (list != null && list.size() > 0) {
			for (Constructor<T> constructor : (Constructor<T>[]) clazz.getConstructors()) {
				if (constructor.isAnnotationPresent(Tag.class) && tag == constructor.getAnnotation(Tag.class).value()) {
					List<T> result = new ArrayList<>(list.size());
					try {
						for (Object[] data : list) {
							result.add(constructor.newInstance(data));
						}
						return result;
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			}
			throw new RuntimeException(clazz.getName() + "constructor need use annotation " + Tag.class.getName());
		}
		return new ArrayList<>();
	}

	/**
	 * 通过无参构造函数映射
	 * 有参构造函数重复时,使用set方法注入
	 * */
	public static <T> List<T> mapping(List<Object[]> list, Class<T> clazz, String ...properties) {
		List<T> result = new ArrayList<>(list.size());
		try {
			Constructor<T> constructor = clazz.getConstructor();
			for (Object[] data : list) {
				T t = constructor.newInstance();
				for (int i = 0; i < properties.length; i++) {
					PropertyDescriptor descriptor = new PropertyDescriptor(properties[i], clazz);
					Method writeMethod = descriptor.getWriteMethod();
					writeMethod.invoke(t, data[i]);
				}
				result.add(t);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

}
