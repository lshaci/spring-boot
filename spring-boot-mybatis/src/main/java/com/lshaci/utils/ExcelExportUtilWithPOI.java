package com.lshaci.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

/**
 * 使用POI导出EXCEL
 * 
 * @author lshaci
 *
 */
public class ExcelExportUtilWithPOI {
	
	/**
	 * 使用POI创建Excel
	 * 
	 * @param sheetName				Excel标签名字
	 * @param firstTitleName		一级标题名字（第一行合并单元格内）
	 * @param secondTitleNames		二级标题名字集合（第二行）
	 * @param secondTitleColumns	二级标题所跨列的集合
	 * @param columnHeaders			列标题集合（第三行）
	 * @param fieldNames			对象字段名集合
	 * @param datas					数据集合
	 * @param otherData				其它的数据（表格最后一行合并的单元格内）
	 * 
	 * @return	返回流信息
	 */
	public static <T> InputStream createWorkBook(String sheetName, String firstTitleName, List<String> secondTitleNames, List<Integer> secondTitleColumns,
			List<String> columnHeaders, List<String> fieldNames, List<T> datas, String otherData) {
		try (
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				HSSFWorkbook workbook = new HSSFWorkbook();
			) {
			// 声明一个工作薄
			// 创建一个sheet
			HSSFSheet sheet = workbook.createSheet(sheetName);
			// 设置列宽
			sheet.setDefaultColumnWidth(12);
			// 设置序号列的宽度
			sheet.setColumnWidth(0, 1500);
			
			// 设置一级标题行
			setFirstTitleRow(workbook, sheet, firstTitleName, columnHeaders.size());
			// 设置二级标题行
			setSecondTitleRows(workbook, sheet, secondTitleNames, secondTitleColumns);
			// 设置列的标题
			setColumnTitles(workbook, sheet, columnHeaders);
			List<String[]> rowDatas = javaBean2StringArrays(datas, fieldNames);
			// 设置表格中的内容
			setSheetRows(workbook, sheet, rowDatas);
			// 如果其它数据存在，则进行填充
			if (otherData != null && otherData != "") {
				setOtherDataRows(workbook, sheet, otherData, 3 + rowDatas.size(), columnHeaders.size());
			}
			
			workbook.write(os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("创建excel表格出错");
		}
		
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
		row.setHeight((short) 1600);
		CellRangeAddress region = new CellRangeAddress(0, 0, 0, size);
		sheet.addMergedRegion(region);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue(firstTitleName);
		cell.setCellStyle(createFirstTitleStyle(workbook));
		
		// 设置合并单元格的边框
		setMergeCellBorder(region, sheet);
	}
	
	/**
	 * 创建一级标题样式
	 * 
	 */
	private static HSSFCellStyle createFirstTitleStyle(HSSFWorkbook workbook) {
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 通过RGB获取相似的颜色
		HSSFPalette customPalette = workbook.getCustomPalette();  
		HSSFColor color = customPalette.findSimilarColor(102, 102, 153);
		
		// 设置这些样式
		// 设置单元格填充颜色
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		// 设置水平居中
		style.setAlignment(HorizontalAlignment.CENTER);
		// 设置垂直居中
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		// 设置字体颜色
		font.setColor(color.getIndex());
		// 设置字体大小
		font.setFontHeightInPoints((short) 20);
		// 是否加粗
		font.setBold(true);
		// 设置字体
		font.setFontName("华文楷体");
		
		// 把字体应用到当前的样式
		style.setFont(font);
		// 使用 \n 换行设置为true
		style.setWrapText(true);
		
		return style;
	}
	
	/**
	 * 设置表格二级标题行
	 * 
	 * @param workbook				工作簿
	 * @param sheet					标签
	 * @param secondTitleNames		二级标题名称集合
	 * @param secondTitleColumns	二级标题所跨列数集合
	 */
	private static void setSecondTitleRows(HSSFWorkbook workbook, HSSFSheet sheet, List<String> secondTitleNames,
			List<Integer> secondTitleColumns) {
		if (secondTitleNames.isEmpty() || secondTitleColumns.isEmpty() || 
				secondTitleNames.size() != secondTitleColumns.size()) {
			throw new RuntimeException("二级标题名对应所跨的列数量不一致！");
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
				setMergeCellBorder(region, sheet);
			}
		}
	}

	/**
	 * 创建二级标题样式
	 */
	private static HSSFCellStyle createSecondTitleStyle(HSSFWorkbook workbook) {
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFPalette customPalette = workbook.getCustomPalette();  
		HSSFColor color = customPalette.findSimilarColor(102, 102, 153);
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		// 设置表格边框
		setBorder(style);
		
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(color.getIndex());
		font.setFontHeightInPoints((short) 12);
		font.setBold(true);
		font.setFontName("微软雅黑");
		// 把字体应用到当前的样式
		style.setFont(font);
		
		return style;
	}
	/**
	 * 设置表格的列标题
	 * 
	 * @param workbook 	工作薄
	 * @param sheet		表格中的一个标签页
	 * @param headers	列标题名称的字符串数组
	 * @throws ExcelHanlderException 
	 */
	private static void setColumnTitles(HSSFWorkbook workbook, HSSFSheet sheet, List<String> columnHeaders) {
		if (columnHeaders.isEmpty()) {
			throw new RuntimeException("列标题集合为空！");
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
	 */
	private static HSSFCellStyle createColumnTitleStyle(HSSFWorkbook workbook) {
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 根据RGB获取相似的颜色
		HSSFPalette customPalette = workbook.getCustomPalette();  
		HSSFColor color = customPalette.findSimilarColor(102, 102, 153);
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		setBorder(style);
		// 设置水平和垂直居中
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(color.getIndex());
		font.setFontHeightInPoints((short) 10);
		font.setBold(true);
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
	private static void setSheetRows(HSSFWorkbook workbook, HSSFSheet sheet, List<String[]> rowDatas) {
		if (rowDatas.isEmpty()) {
			throw new RuntimeException("内容集合为空！");
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
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 10);
		font.setFontName("宋体");
		// 把字体应用到当前的样式
		style.setFont(font);
		
		return style;
	}
	/**
	 * 设置合并单元格的边框
	 * 
	 * @param region 	合并的单元格
	 * @param sheet		标签
	 */
	private static void setMergeCellBorder(CellRangeAddress region, HSSFSheet sheet) {
		RegionUtil.setBorderBottom(1, region, sheet);
		RegionUtil.setBorderLeft(1, region, sheet);
		RegionUtil.setBorderRight(1, region, sheet);
		RegionUtil.setBorderTop(1, region, sheet);
	}
	
	/**
	 * 设置表格边框
	 * 
	 * @param style 表格样式
	 */
	private static void setBorder(HSSFCellStyle style) {
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
	}
	
	/**
	 * 设置一些其它数据，在表格最后一行合并单元格内
	 * 
	 * @param workbook
	 * @param sheet
	 * @param otherData
	 * @param rowIndex
	 * @param size
	 */
	private static void setOtherDataRows(HSSFWorkbook workbook, HSSFSheet sheet, String otherData, int rowIndex, int size) {
		HSSFRow row = sheet.createRow(rowIndex);
		row.setHeight((short) 320);
		CellRangeAddress region = new CellRangeAddress(rowIndex, rowIndex, 0, size);
		sheet.addMergedRegion(region);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue(otherData);
		HSSFCellStyle style = createContentStyle(workbook);
		style.setAlignment(HorizontalAlignment.LEFT);
		style.setWrapText(true);
		cell.setCellStyle(style);
		
		// 设置合并单元格的边框
		setMergeCellBorder(region, sheet);
	}

	/**
	 * javaBean转换为String数组集合
	 * 
	 * @param datas		需要转换的数据集合(对象类型，符合javaBean规范)
	 * @param fields	指定需要转换的字段集合
	 * @return
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	private static <T> List<String[]> javaBean2StringArrays(List<T> datas, List<String> fieldNames) 
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (datas.isEmpty()) {
			throw new RuntimeException("数据为空！");
		}
		
		List<String[]> rows = new ArrayList<>();
		
		Class<?> clazz = datas.get(0).getClass();
		if (fieldNames.isEmpty()) {
			throw new RuntimeException("字段名为空！");
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
