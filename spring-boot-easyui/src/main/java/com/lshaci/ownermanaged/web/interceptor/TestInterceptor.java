package com.lshaci.ownermanaged.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lshaci.ownermanaged.utils.ContextUtils;

public class TestInterceptor implements HandlerInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(TestInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("请求地址：" + request.getRequestURL());
		logger.debug("访问IP：" + request.getRemoteAddr());
		ContextUtils.putReqAndResp(request, response);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		ContextUtils.remove();
	}

}
