package com.lshaci.web.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * 日期转换器<br>
 * 将格式为<b>yyyy-MM-dd HH:mm:ss</b>字符串日期类型，转换为<b>java.util.Date</b>
 * 
 * @author lshaci
 *
 */
public class String2DateConverter implements Converter<String, Date> {

	private final static Logger logger = LoggerFactory.getLogger(String2DateConverter.class);

	/**
	 * 长日期格式
	 */
	private static final String longDateFormatStr = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 短日期格式
	 */
	private static final String shortDateFormatStr = "yyyy-MM-dd";

	@Override
	public Date convert(String source) {
		if (StringUtils.isEmpty(source)) {
			return null;
		}
		source = source.trim();
		try {
			if (source.contains("-")) {
				SimpleDateFormat formatter;
				if (source.contains(":")) {
					formatter = new SimpleDateFormat(longDateFormatStr);
				} else {
					formatter = new SimpleDateFormat(shortDateFormatStr);
				}
				return formatter.parse(source);
			} else if (source.matches("^\\d+$")) {
				return new Date(Long.parseLong(source));
			}
			return null;
		} catch (ParseException e) {
			logger.debug("日期字符串格式不正确。");
			return null;
		}
	}

}
