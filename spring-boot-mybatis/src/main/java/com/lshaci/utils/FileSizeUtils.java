package com.lshaci.utils;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSizeUtils {
	
	public static Integer i = 0;

	private static final Logger logger = LoggerFactory.getLogger(FileSizeUtils.class);

	/**
	 * 文件大小类型
	 */
	public static enum SizeType {
		/**
		 * B
		 */
		SIZETYPE_B, 
		/**
		 * KB
		 */
		SIZETYPE_KB,
		/**
		 * MB
		 */
		SIZETYPE_MB,
		/**
		 * GB
		 */
		SIZETYPE_GB,
	}

	/**
	 * 获取文件指定文件的指定单位的大小
	 * 
	 * @param filePath 		 文件路径
	 * @param sizeType 		获取大小的类型1为B、2为KB、3为MB、4为GB
	 * 
	 * @return double值的大小
	 */
	public static double getFileOrFilesSize(String filePath, SizeType sizeType) {
		File file = new File(filePath);
		long blockSize = 0;
		try {
			if (file.isDirectory()) {
				blockSize = getFileSizes(file);
			} else {
				blockSize = getFileSize(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取文件大小", "获取失败!");
		}
		return FormetFileSize(blockSize, sizeType);
	}

	/**
	 * 调用此方法自动计算指定文件或指定文件夹的大小
	 * 
	 * @param filePath 		文件路径
	 * 
	 * @return 计算好的带B、KB、MB、GB的字符串
	 */
	public static String getAutoFileOrFilesSize(String filePath) {
		File file = new File(filePath);
		long blockSize = 0;
		try {
			if (file.isDirectory()) {
				blockSize = getFileSizes(file);
			} else {
				blockSize = getFileSize(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取文件大小", "获取失败!");
		}
		return formetFileSize(blockSize);
	}

	/**
	 * 获取指定文件大小
	 * 
	 * @param file		需要获取大小的文件
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	private static long getFileSize(File file) throws Exception {
		long size = 0;
		if (file.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(file);
			size = fis.available();
		} else {
			file.createNewFile();
			logger.error("获取文件大小", "文件不存在!");
		}
		return size;
	}

	/**
	 * 获取指定文件夹
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	private static long getFileSizes(File f) throws Exception {
		long size = 0;
		File flist[] = f.listFiles();
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFileSizes(flist[i]);
			} else {
				size = size + getFileSize(flist[i]);
			}
		}
		return size;
	}

	/**
	 * 转换文件大小
	 * 
	 * @param fileS
	 * @return
	 */
	private static Double formetFileSize(Double fileSize) {
		if (fileSize >= 1024) {
			i++;
			return formetFileSize(fileSize / 1024);
		}
		return fileSize;
	}
	
	
	private static String formetFileSize(Long fileSize) {
		Double formetFileSize = formetFileSize(Double.parseDouble(fileSize.toString()));
		DecimalFormat df = new DecimalFormat("#.00");
		String sizeType = "B";
		switch (i) {
			case 0: sizeType = "B"; break;
			case 1: sizeType = "KB"; break;
			case 2: sizeType = "MB"; break;
			case 3: sizeType = "GB"; break;
			case 4: sizeType = "TB"; break;
			case 5: sizeType = "PB"; break;
			case 6: sizeType = "EB"; break;
			case 7: sizeType = "ZB"; break;
			case 8: sizeType = "YB"; break;
			case 9: sizeType = "BB"; break;
			case 10: sizeType = "NB"; break;
			case 11: sizeType = "DB"; break;

			default: throw new RuntimeException("大小超出限制");
		}
		return df.format(formetFileSize) + sizeType;
	}

	/**
	 * 转换文件大小,指定转换的类型
	 * 
	 * @param fileS
	 * @param sizeType
	 * @return
	 */
	private static double FormetFileSize(long fileS, SizeType sizeType) {
		DecimalFormat df = new DecimalFormat("#.00");
		double fileSizeLong = 0;
		switch (sizeType) {
		case SIZETYPE_B:
			fileSizeLong = Double.valueOf(df.format((double) fileS));
			break;
		case SIZETYPE_KB:
			fileSizeLong = Double.valueOf(df.format((double) fileS / 1024));
			break;
		case SIZETYPE_MB:
			fileSizeLong = Double.valueOf(df.format((double) fileS / 1048576));
			break;
		case SIZETYPE_GB:
			fileSizeLong = Double.valueOf(df.format((double) fileS / 1073741824));
			break;
		default:
			break;
		}
		return fileSizeLong;
	}
}