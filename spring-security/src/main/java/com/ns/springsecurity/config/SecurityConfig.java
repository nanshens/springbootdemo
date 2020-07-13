package com.ns.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author ns
 * @create 2020-06-28
 * prePostEnabled：使用表达式实现方法级别的控制，如：@PreAuthorize("hasRole('ADMIN')")
 * securedEnabled: 开启 @Secured 注解过滤权限，如：@Secured("ROLE_ADMIN")
 * jsr250Enabled: 开启 @RolesAllowed 注解过滤权限，如：@RolesAllowed("ROLE_ADMIN")
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //保证post之前的注解可以使用
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final static String[] ALL_PERMIT_URLS = {
			"/login"
	};
	public static String ADMIN = "ROLE_ADMIN";

	@Autowired AuthTokenFilter authTokenFilter;
	@Autowired AuthExceptionEntryPoint authExceptionEntryPoint;

	@Override
	@Bean
	public UserDetailsService userDetailsService() {
		return new SecurityUserServiceImpl();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//		List<String> allowedOriginsUrl = new ArrayList<>();
//		allowedOriginsUrl.add("http://localhost:54546");
//		allowedOriginsUrl.add("http://127.0.0.1:54546");
//		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
//		config.setAllowedOrigins(allowedOriginsUrl);
//		config.addAllowedHeader("*");
//		config.addAllowedMethod("*");
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", config);
//		return source;
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//下面这两行配置表示在内存中配置了两个用户
//		auth.inMemoryAuthentication()
//				.withUser("admin1").roles("admin").password("admin1")
//				.and()
//				.withUser("user").roles("user").password("user");
		auth.userDetailsService(userDetailsService())
				.passwordEncoder(passwordEncoder());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers(ALL_PERMIT_URLS).permitAll()
				.antMatchers("/admin/**") .hasAnyAuthority(ADMIN)
				.anyRequest().authenticated()
				.and()
				.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling().authenticationEntryPoint(authExceptionEntryPoint)
//				.cors()
				.and()
				.csrf().disable();
	}
}
