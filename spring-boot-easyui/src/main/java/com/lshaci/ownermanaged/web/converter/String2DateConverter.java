package com.lshaci.ownermanaged.web.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

/**
 * 字符串转换为日期Converter
 */
public class String2DateConverter implements Converter<String, Date> {

	private Logger logger = LoggerFactory.getLogger(String2DateConverter.class);

	/**
	 * 长日期格式<b>yyyy-MM-dd HH:mm:ss</b>
	 */
	private static final String LONG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 断日期格式<b>yyyy-MM-dd</b>
	 */
	private static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";

	@Override
	public Date convert(String source) {
		if (StringUtils.isEmpty(source))
			return null;

		source = source.trim();

		try {
			if (source.contains("-")) {
				SimpleDateFormat formatter;
				if (source.contains(":")) {
					formatter = new SimpleDateFormat(LONG_DATE_FORMAT);
				} else {
					formatter = new SimpleDateFormat(SHORT_DATE_FORMAT);
				}
				return formatter.parse(source);
			} else if (source.matches("^\\d+$")) {
				return new Date(Long.parseLong(source));
			}
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error("The date string format is incorrect. -->" + source);
		}
		return null;
	}

}
