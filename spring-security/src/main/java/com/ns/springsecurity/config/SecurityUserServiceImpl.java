package com.ns.springsecurity.config;

import com.ns.springsecurity.entity.SecurityUser;
import com.ns.springsecurity.entity.SystemUser;
import com.ns.springsecurity.repo.SystemUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author ns
 */
@Component
public class SecurityUserServiceImpl implements UserDetailsService {
	@Autowired private SystemUserRepo systemUserRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<SystemUser> user = systemUserRepo.getByUsername(username);

		if (user.isPresent()) {
			return new SecurityUser(user.get());
		} else {
			throw new UsernameNotFoundException(String.format("%s.这个用户不存在", username));
		}
	}
}
