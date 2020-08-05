package com.ns.restrepositories.repo;

import com.ns.restrepositories.entity.Student;
import org.springframework.stereotype.Repository;

/**
 * @author nanshen
 */
@Repository
public interface StudentRepo extends BaseRepository<Student, String> {
	Student findByName(String name);
}
