package com.ns.springsecurity.config;

import com.alibaba.fastjson.JSONObject;
import com.ns.springsecurity.entity.SecurityUser;
import com.ns.springsecurity.exception.CustomSecurityException;
import com.ns.springsecurity.utils.HttpUtil;
import com.ns.springsecurity.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author ns
 * @create 2020-06-28
 */
@Component
public class AuthTokenFilter extends OncePerRequestFilter {
	@Autowired private JwtUtil jwtUtil;
	@Autowired private AuthenticationManager authManager;
	@Autowired private AuthenticationEntryPoint authenticationEntryPoint;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		try {
			if (request.getRequestURI().contains("/login")) {
				loginAuth(request, response);
			} else {
				tokenAuth(request, response, filterChain);
			}
		} catch (AuthenticationException e) {
			authenticationEntryPoint.commence(request, response, e);
		}
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		SecurityUser securityUser = jwtUtil.getUserFromToken(token);
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(securityUser.getUsername(), null, securityUser.getAuthorities());
//		authToken.setDetails(claims);
		return authToken;
	}

	private void loginAuth(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AuthenticationException {
		Map<String, String> loginInfo = HttpUtil.getDataFromHttpReq(request);
		if (loginInfo == null) {
			response.sendError(HttpStatus.FORBIDDEN.value(), "无用户信息");
		} else {
			Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginInfo.get("username"), loginInfo.get("password")));
			String jwt = jwtUtil.generateToken((UserDetails) auth.getPrincipal());
			JSONObject result = new JSONObject();
			result.put("token", jwt);
			HttpUtil.sendResult(response, HttpStatus.OK.value(), result.toJSONString());
		}
	}

	private void tokenAuth(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException, AuthenticationException {
		String token = jwtUtil.getTokenFromRequest(request);
		if (token == null) {
			throw new CustomSecurityException("无效token");
		}

		UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(token);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(request, response);
	}

}
