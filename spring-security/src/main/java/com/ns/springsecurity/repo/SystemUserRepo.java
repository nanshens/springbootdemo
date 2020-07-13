package com.ns.springsecurity.repo;

import com.ns.springsecurity.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ns
 * @create 2020-06-28
 */
@Repository
public interface SystemUserRepo extends JpaRepository<SystemUser, String> {
	Optional<SystemUser> getByUsername(String username);
}
