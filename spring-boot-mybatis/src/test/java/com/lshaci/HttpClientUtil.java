package com.lshaci;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * HttpClientUtil工具
 * 
 * @author heaven
 *
 */
public class HttpClientUtil {

	private static CloseableHttpClient client = HttpClients.createDefault();

	/**
	 * post请求方式
	 * 
	 * @param url URL地址
	 * @param params 参数
	 * @param headers 请求头
	 * @return
	 */
	public static String httpPost(String url, Map<String, Object> paramsMap, Map<String, Object> headersMap) {
		String body = null;
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;
		;
		try {
			// 设置请求头
			if (headersMap != null) {
				Header[] headers = headerMapToCollection(headersMap);
				httpPost.setHeaders(headers);
			}
			// 设置参数
			if (paramsMap != null) {
				List<NameValuePair> params = paramsMapToCollection(paramsMap);
				httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			}
			response = client.execute(httpPost);
			// 获取返回数据
			if (response.getEntity() != null) {
				body = EntityUtils.toString(response.getEntity());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return body;
	}

	/**
	 * get请求方式
	 * 
	 * @param url
	 *            请求地址
	 * @param paramsMap
	 *            参数
	 * @return
	 */
	public static String httpGet(String url, Map<String, Object> paramsMap) {
		String body = null;
		CloseableHttpResponse response = null;
		try {
			HttpGet httpget = new HttpGet(url);
			String URI = httpget.getURI().toString();
			// 设置参数
			if (paramsMap != null) {
				String paramStr = EntityUtils.toString(new UrlEncodedFormEntity(paramsMapToCollection(paramsMap)));
				URI = URI + "?" + paramStr;
			}
			httpget.setURI(new URI(URI));
			response = client.execute(httpget);
			// 获取返回值
			if (response.getEntity() != null) {
				body = EntityUtils.toString(response.getEntity());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return body;
	}

	/**
	 * 将Map装的header信息转成Header数组
	 * 
	 * @param headersMap
	 * @return
	 */
	private static Header[] headerMapToCollection(Map<String, Object> headersMap) {
		if (headersMap != null && headersMap.size() > 0) {
			Header[] headers = new Header[headersMap.size()];
			int headersIndex = 0;
			Iterator<Entry<String, Object>> entries = headersMap.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry<String, Object> entry = (Map.Entry<String, Object>) entries.next();
				Header headerObj = new BasicHeader(entry.getKey(), entry.getValue().toString());
				headers[headersIndex] = headerObj;
				headersIndex++;
			}
			return headers;
		}
		return null;
	}

	/**
	 * 将Map装的参数转为List
	 * 
	 * @param paramsMap
	 * @return
	 */
	private static List<NameValuePair> paramsMapToCollection(Map<String, Object> paramsMap) {
		if (paramsMap != null && paramsMap.size() > 0) {
			List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
			Iterator<Entry<String, Object>> entries = paramsMap.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry<String, Object> entry = (Map.Entry<String, Object>) entries.next();
				paramsList.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
			}
			return paramsList;
		}
		return null;
	}

	/*
	 * public static void main(String[] args) throws Exception {
	 * Map<String,Object> params = new HashMap<String, Object>();
	 * params.put("q", "Bigbang"); String body =
	 * httpGet("http://www.hyent.cc/search.php",params);
	 * System.out.println(body); }
	 */
}
