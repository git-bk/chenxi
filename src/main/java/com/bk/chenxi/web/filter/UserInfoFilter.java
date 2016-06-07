package com.bk.chenxi.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bk.chenxi.biz.UserBo;
import com.bk.chenxi.dto.UserDto;
import com.bk.chenxi.exception.ChanceValidateException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class UserInfoFilter implements Filter {

	private final Logger logger = LoggerFactory.getLogger(UserInfoFilter.class);

	@Autowired
	private UserBo userBo;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		UserDto user = null;
		try {
			user = userBo.getLocalUserDto();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if (user != null) {
			HttpServletResponse re = (HttpServletResponse) response;
			HttpServletRequest requ = (HttpServletRequest) request;
			// 将用户信息存放到session
			try {
				if (requ.getSession().getAttribute("userInfo") == null) {
					UserDto u = userBo.getLocalUserDto();
					u.setPassword(null);
					requ.getSession().setAttribute("userInfo", u);
				}
			} catch (ChanceValidateException e) {
				logger.error(e.getMessage(), e);
			}
			if (StringUtils.isBlank(user.getAccount())
					|| StringUtils.isBlank(user.getEmail())
					|| StringUtils.isBlank(user.getRealname())) {
				String uri = requ.getRequestURI();
				if (!uri.contains("/chenxi/personal/")) {
					re.sendRedirect("/chenxi/personal/personal.htm?supplement=true");
					return;
				}
			}
		}
		chain.doFilter(request, response);// 把处理发送到下一个过滤器
	}

	@Override
	public void destroy() {

	}

}
