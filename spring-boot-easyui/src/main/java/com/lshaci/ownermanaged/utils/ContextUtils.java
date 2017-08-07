package com.lshaci.ownermanaged.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.Assert;

/**
 * 上下文工具
 */
public abstract class ContextUtils {
	
	/**
	 * 缓存HttpServletRequest的key
	 */
	private final static String REQUEST = "request";
	
	/**
	 * 缓存HttpServletResponse的key
	 */
	private final static String RESPONSE = "response";
	
	/**
	 * 缓存HttpSession的key
	 */
	private final static String SESSION = "session";
	
	/**
	 * 线程局部变量对象
	 */
	private static ThreadLocal<Map<String, Object>> local = new ThreadLocal<>();
	
	/**
	 * 从当前线程中获取线程局部变量
	 * 
	 * @return	返回当前线程的局部变量
	 */
	private static Map<String, Object> getCache() {
		Map<String, Object> cache = local.get();
		if (cache == null) {
			cache = new HashMap<>();
			local.set(cache);
		}
		return cache;
	}
	
	/**
	 * 在当前线程局部变量中缓存对象
	 * 
	 * @param key		缓存对象的key
	 * @param value		缓存对象
	 */
	private static void put(String key, Object value) {
		getCache().put(key, value);
	}
	
	/**
	 * 在当前线程局部变量中获取缓存对象
	 * 
	 * @param key	缓存对象的key
	 * @return	返回缓存的缓存对象
	 */
	private static Object get(String key) {
		return getCache().get(key);
	}
	
	/**
	 * 在当前线程局部变量中缓存HttpServletRequest & HttpSession
	 * 
	 * @param request	HttpServletRequest
	 */
	public static void putRequest(HttpServletRequest request) {
		put(REQUEST, request);
		put(SESSION, request.getSession());
	}
	
	/**
	 * 在当前线程局部变量中缓存HttpServletResponse
	 * 
	 * @param response	HttpServletResponse
	 */
	public static void putResponse(HttpServletResponse response) {
		put(RESPONSE, response);
	}
	
	/**
	 * 在当前线程局部变量中缓存HttpServletRequest & HttpSession & HttpServletResponse
	 * 
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 */
	public static void putReqAndResp(HttpServletRequest request, HttpServletResponse response) {
		putRequest(request);
		putResponse(response);
	}
	
	/**
	 * 在当前线程局部变量中获取缓存的HttpServletResponse
	 * 
	 * @return	HttpServletResponse
	 */
	public static HttpServletResponse getResponse() {
		Object obj = get(RESPONSE);
		Assert.notNull(obj, "Response is null!");
		return (HttpServletResponse) obj;
	}
	
	/**
	 * 在当前线程局部变量中获取缓存的HttpServletRequest
	 * 
	 * @return	HttpServletRequest
	 */
	public static HttpServletRequest getRequest() {
		Object obj = get(REQUEST);
		Assert.notNull(obj, "Request is null!");
		return (HttpServletRequest) obj;
	}
	
	/**
	 * 在当前线程局部变量中获取缓存的HttpSession
	 * 
	 * @return	HttpSession
	 */
	public static HttpSession getSession() {
		Object obj = get(SESSION);
		Assert.notNull(obj, "Session is null!");
		return (HttpSession) obj;
	}
	
	/**
	 * 删除当前线程局部变量的当前线程的值
	 */
	public static void remove() {
		local.remove();
	}
	
	/**
	 * 通过当前当前线程局部变量中缓存的HttpServletResponse向页面写出内容
	 * 
	 * @param content		需要写出到页面的内容
	 * @throws IOException
	 */
	public static void responseWrite(byte[] content) throws IOException {
		ServletOutputStream out = getResponse().getOutputStream();
		out.write(content);
		out.close();
	}

}
