package com.ns.restrepositories.repo;

import com.ns.restrepositories.entity.Course;
import org.springframework.stereotype.Repository;

/**
 * @author ns
 * @create 2020-05-30
 */
@Repository
public interface CourseRepo extends BaseRepository<Course, String> {

	
}
