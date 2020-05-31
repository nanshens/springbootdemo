package com.ns.springdatajpa.repo.many2many;

import com.ns.springdatajpa.entity.many2many.Student;
import com.ns.springdatajpa.repo.base.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nanshen
 */
@Repository
public interface StudentRepo extends BaseRepository<Student, String> {
	Student findByName(String name);
}
