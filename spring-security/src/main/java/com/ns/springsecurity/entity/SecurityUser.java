package com.ns.springsecurity.entity;

import io.jsonwebtoken.Claims;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Map;

/**
 * @author ns
 * @create 2020-06-28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class SecurityUser extends SystemUser implements UserDetails {
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private boolean remember;
	private Collection<? extends GrantedAuthority> authorities;


	public SecurityUser(SystemUser systemUser) {
		setId(systemUser.getId());
		setUsername(systemUser.getUsername());
		setPassword(systemUser.getPassword());
		accountNonExpired = true;
		accountNonLocked = true;
		credentialsNonExpired = true;
		enabled = true;
	}
}
