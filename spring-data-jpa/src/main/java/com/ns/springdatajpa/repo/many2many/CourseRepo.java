package com.ns.springdatajpa.repo.many2many;

import com.ns.springdatajpa.entity.many2many.Course;
import com.ns.springdatajpa.repo.base.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ns
 * @create 2020-05-30
 */
@Repository
public interface CourseRepo extends BaseRepository<Course, String> {

	
}
