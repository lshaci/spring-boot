package com.lshaci.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

import com.lshaci.exception.ExcelHanlderException;

/**
 * excel处理相关的工具
 * 
 * @author lshaci
 *
 */
@SuppressWarnings("deprecation")
public class ExcelHanlderUtils {

	/**
	 * 获取创建excel表格的输入流
	 * 
	 * @param firstTitleName		表格一级标题名称
	 * @param secondTitleNames		表格二级标题名称集合
	 * @param secondTitleColumns	表格二级标题所跨列数集合
	 * @param columnHeaders			表格列标题集合
	 * @param rowDatas				表格数据集合
	 * @return
	 * @throws ExcelHanlderException
	 * @throws IOException
	 */
	public static InputStream exportExcel(String firstTitleName, List<String> secondTitleNames, List<Integer> secondTitleColumns, List<String> columnHeaders, List<String[]> rowDatas) 
			throws ExcelHanlderException, IOException {
		if (CommUtils.stringIsEmpty(firstTitleName)) {
			throw new ExcelHanlderException("一级标题为空！");
		}
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 创建一个sheet
		HSSFSheet sheet = workbook.createSheet(firstTitleName);
		// 设置列宽
		sheet.setDefaultColumnWidth(16);
		// 设置序号列的宽度
		sheet.setColumnWidth(0, 1500);
		
		// 设置一级标题行
		setFirstTitleRow(workbook, sheet, firstTitleName, columnHeaders.size());
		// 设置二级标题行
		setSecondTitleRows(workbook, sheet, secondTitleNames, secondTitleColumns);
		// 设置列的标题
		setColumnTitles(workbook, sheet, columnHeaders);
		// 设置表格中的内容
		setSheetRows(workbook, sheet, rowDatas);
		
		workbook.write(os);
		os.close();
		
		return new ByteArrayInputStream(os.toByteArray());
	}

	/**
	 * 设置表格一级标题行
	 * 
	 * @param workbook			工作簿
	 * @param sheet				标签
	 * @param firstTitleName	一级标题名称
	 * @param size				一级标题所跨列数
	 */
	private static void setFirstTitleRow(HSSFWorkbook workbook, HSSFSheet sheet, String firstTitleName, int size) {
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) 600);
		CellRangeAddress region = new CellRangeAddress(0, 0, 0, size);
		sheet.addMergedRegion(region);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue(firstTitleName);
		cell.setCellStyle(createFirstTitleStyle(workbook));
		
		// 设置合并单元格的边框
		setMergeCellBorder(region, sheet, workbook);
	}

	/**
	 * 创建一级标题格式
	 * 
	 * @param workbook
	 * @return
	 */
	private static HSSFCellStyle createFirstTitleStyle(HSSFWorkbook workbook) {
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 15);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("微软雅黑");
		// 把字体应用到当前的样式
		style.setFont(font);
		
		return style;
	}
	
	/**
	 * 设置表格二级标题行
	 * 
	 * @param workbook				工作簿
	 * @param sheet					标签
	 * @param secondTitleNames		二级标题名称集合
	 * @param secondTitleColumns	二级标题所跨列数集合
	 * @throws ExcelHanlderException 
	 */
	private static void setSecondTitleRows(HSSFWorkbook workbook, HSSFSheet sheet, List<String> secondTitleNames,
			List<Integer> secondTitleColumns) throws ExcelHanlderException {
		if (CommUtils.listIsEmpty(secondTitleNames) || CommUtils.listIsEmpty(secondTitleColumns) || 
				secondTitleNames.size() != secondTitleColumns.size()) {
			throw new ExcelHanlderException("二级标题名对应所跨的列数量不一致！");
		}
		HSSFRow row = sheet.createRow(1);
		row.setHeight((short) 400);
		for (int i = 0; i < secondTitleNames.size(); i++) {
			Integer now = secondTitleColumns.get(i) - 1;
			Integer last = i == 0 ? 0 : secondTitleColumns.get(i - 1);
			HSSFCell cell = row.createCell(last);;
			cell.setCellValue(secondTitleNames.get(i));
			cell.setCellStyle(createSecondTitleStyle(workbook));
			if (now > 0) {
				CellRangeAddress region = new CellRangeAddress(1, 1, last, last + now);
				sheet.addMergedRegion(region);
				
				// 设置合并单元格的边框
				setMergeCellBorder(region, sheet, workbook);
			}
		}
		
	}

	/**
	 * 创建二级标题格式
	 * 
	 * @param workbook
	 * @return
	 */
	private static HSSFCellStyle createSecondTitleStyle(HSSFWorkbook workbook) {
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		// 设置表格边框
		setBorder(style);
		
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("微软雅黑");
		// 把字体应用到当前的样式
		style.setFont(font);
		
		return style;
	}

	/**
	 * 设置合并单元格的边框
	 * 
	 * @param region 	合并的单元格
	 * @param sheet		标签
	 * @param workbook	工作簿
	 */
	private static void setMergeCellBorder(CellRangeAddress region, HSSFSheet sheet, HSSFWorkbook workbook) {
		RegionUtil.setBorderBottom(1, region, sheet, workbook);
		RegionUtil.setBorderLeft(1, region, sheet, workbook);
		RegionUtil.setBorderRight(1, region, sheet, workbook);
		RegionUtil.setBorderTop(1, region, sheet, workbook);
	}
	
	/**
	 * 设置表格的列标题
	 * 
	 * @param workbook 	工作薄
	 * @param sheet		表格中的一个标签页
	 * @param headers	列标题名称的字符串数组
	 * @throws ExcelHanlderException 
	 */
	private static void setColumnTitles(HSSFWorkbook workbook, HSSFSheet sheet, List<String> columnHeaders) 
			throws ExcelHanlderException {
		if (CommUtils.listIsEmpty(columnHeaders)) {
			throw new ExcelHanlderException("列标题集合为空！");
		}
		HSSFRow row = sheet.createRow(2);
		row.setHeight((short) 360);
		HSSFCellStyle columnTitleStyle = createColumnTitleStyle(workbook);
		for (int i = 0; i <= columnHeaders.size(); i++) {
			HSSFCell cell = row.createCell(i);
			String value = i == 0 ? "序号" : columnHeaders.get(i - 1);
			cell.setCellValue(value);
			cell.setCellStyle(columnTitleStyle);
		}
	}
	
	/**
	 * 创建列标题栏样式
	 * 
	 * @param workbook	工作薄
	 * @return
	 */
	private static HSSFCellStyle createColumnTitleStyle(HSSFWorkbook workbook) {
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		setBorder(style);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 11);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		// 把字体应用到当前的样式
		style.setFont(font);
		
		return style;
	}
	
	/**
	 * 设置表格中的内容
	 * 
	 * @param workbook 	工作薄
	 * @param sheet		表格中的一个标签页
	 * @param rows		内容字符串数组集合
	 * @throws ExcelHanlderException 
	 */
	private static void setSheetRows(HSSFWorkbook workbook, HSSFSheet sheet, List<String[]> rowDatas) 
			throws ExcelHanlderException {
		if (CommUtils.listIsEmpty(rowDatas)) {
			throw new ExcelHanlderException("内容集合为空！");
		}
		HSSFCellStyle contentStyle = createContentStyle(workbook);
		for (int i = 0; i < rowDatas.size(); i++) {
			HSSFRow row = sheet.createRow(3 + i);
			row.setHeight((short) 320);
			String[] rowData = rowDatas.get(i);
			for (int j = 0; j <= rowData.length; j++) {
				HSSFCell cell = row.createCell(j);
				String value = j == 0 ? (i + 1) + "" : rowData[j - 1];
				cell.setCellValue(value);
				cell.setCellStyle(contentStyle);
			}
		}
	}

	/**
	 * 创建表格内容样式
	 * 
	 * @param workbook	工作薄
	 * @return
	 */
	private static HSSFCellStyle createContentStyle(HSSFWorkbook workbook) {
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.WHITE.index);
//		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		setBorder(style);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 10);
		font.setFontName("楷体");
		// 把字体应用到当前的样式
		style.setFont(font);
		
		return style;
	}
	
	/**
	 * 设置表格边框
	 * 
	 * @param style 表格样式
	 */
	private static void setBorder(HSSFCellStyle style) {
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	}

	/**
	 * javaBean转换为String数组集合
	 * 
	 * @param datas		需要转换的数据集合(对象类型，符合javaBean规范)
	 * @param fields	指定需要转换的字段集合
	 * @param isAll		是否全部转换
	 * @return
	 * @throws ExcelHanlderException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static <T> List<String[]> javaBean2StringArrays(List<T> datas, List<String> fieldNames, boolean isAll) 
			throws ExcelHanlderException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (CommUtils.listIsEmpty(datas)) {
			throw new ExcelHanlderException("数据为空！");
		}
		
		List<String[]> rows = new ArrayList<>();
		
		Class<?> clazz = datas.get(0).getClass();
		if (isAll) {
			Field[] fields = clazz.getDeclaredFields();
			int length = fields.length;
			fieldNames = new ArrayList<>();
			for (int i = 0; i < length; i++) {
				fieldNames.add(fields[i].getName());
			}
		} else {
			if (CommUtils.listIsEmpty(fieldNames)) {
				throw new ExcelHanlderException("字段名为空！");
			}
		}
		
		Method[] methods = getMethods(clazz, fieldNames);
		
		for (T data : datas) {
			int length = methods.length;
			String[] row = new String[length];
			for (int i = 0; i < length; i++) {
				Object obj = methods[i].invoke(data);
				if (obj != null) {
					row[i] = obj.toString();
				} else {
					row[i] = "";
				}
			}
			rows.add(row);
		}
		return rows;
	}
	
	/**
	 * 根据字段名获取get方法
	 * 
	 * @param clazz 		需要获取get方法对象的Class
	 * @param fieldNames 	字段名字符串集合
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	private static Method[] getMethods(Class<?> clazz, List<String> fieldNames) 
			throws NoSuchMethodException, SecurityException {
		int length = fieldNames.size();
		Method[] methods = new Method[length];
		for (int i = 0; i < length; i++) {
			String fieldName = fieldNames.get(i);
			String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			methods[i] = clazz.getMethod(methodName);
		}
		return methods;
	}
}