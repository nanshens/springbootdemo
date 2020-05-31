package com.ns.springdatajpa.audit;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author ns
 */
@Configuration
public class UserAuditorAware implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		//如使用了security,请使用相关方法获取操作用户信息
		return Optional.of("admin");
	}
}
