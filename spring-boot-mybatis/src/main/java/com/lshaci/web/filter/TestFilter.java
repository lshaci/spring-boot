package com.lshaci.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter("/*")
public class TestFilter implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(TestFilter.class);

	@Override
	public void init(FilterConfig paramFilterConfig) throws ServletException {
		logger.debug("TestFilter.init");
	}

	@Override 
	public void doFilter(ServletRequest paramServletRequest, ServletResponse paramServletResponse,
			FilterChain paramFilterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) paramServletRequest;
		logger.debug("请求地址：" + req.getRequestURL());
		logger.debug("访问IP：" + req.getRemoteAddr());
		paramFilterChain.doFilter(paramServletRequest, paramServletResponse);
	}

	@Override
	public void destroy() {
		
	}


}
