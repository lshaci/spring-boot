package com.lshaci;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

public class SimpleTest {
	
	@Test
	public void testName() throws Exception {
		Integer i = 1_2;
		System.out.println(i);
//		Integer[] numbers = {1, 5, 2, 9, 4, 8, 7};
//		maopao(numbers);
//		System.out.println(Arrays.toString(numbers));
//		Arrays.sort(numbers);
	}
	
	public int find(Integer[] numbers, int searchKey) {
		int start = 0;
		int end = numbers.length;
		int current;
		while(true) {
			if (start > end ) {
				return -1;
			}
			current = (end + start) / 2;
			if (numbers[current] == searchKey) {
				return current;
			} 
			if (numbers[current] > searchKey) {
				end = current - 1;
			} else  {
				start = current + 1;
			}
		}
	}
	
	public void xuanze(Integer[] numbers) {
		for (int i = 0; i < numbers.length - 1; i++) {
			for (int j = i + 1; j < numbers.length; j++) {
				if (numbers[i] > numbers[j]) {
					 numbers[i] = numbers[i] + numbers[j];
					 numbers[j] = numbers[i] - numbers[j];
					 numbers[i] = numbers[i] - numbers[j];
				}
			}
		}
	}
	
	public void maopao(Integer[] numbers) {
		int t = 0;
		for (int i = 0; i < numbers.length; i++) {
			for (int j = 0; j < numbers.length; j++) {
				t++;
				if (numbers[i] > numbers[j]) {
					numbers[i] = numbers[i] + numbers[j];
					numbers[j] = numbers[i] - numbers[j];
					numbers[i] = numbers[i] - numbers[j];
				}
			}
			System.out.println(Arrays.toString(numbers));
		}
		System.out.println(t);
	}
	
	@Test
	public void captureHtml() throws Exception {  
		File x = new File("D:/123.txt");
		PrintStream ps = new PrintStream(new FileOutputStream(x));
		for (int i = 0; i < 557; i++) {
			int j = 5650660 + i;
			String url = "http://www.qb5200.com/xiaoshuo/60/60101/" + j + ".html";
			Document document = Jsoup.connect(url).get();
			Element titleElement = document.getElementById("title");
			Element contentElement = document.getElementById("content");
			String title = titleElement.text();
			String content = contentElement.html().replace("&nbsp;", " ").replace("<br>", "");
			ps.append(title);
			ps.append("\n\n");
			ps.append(content);
			ps.append("\n\n");
			if (i % 100 == 0 && i > 1) {
				Thread.sleep(10000);
			}
		}
		ps.close();
	}
	
	@Test
	public void testName6() throws Exception {
		Integer[] arrays = { 1, 2, 3, 4, 5, 1, 1, 2, 4, 5 };
		Map<Integer, String> maps = new HashMap<>();
		for (int i = 0; i < arrays.length; i++) {
			if (maps.get(arrays[i]) == null) {
				maps.put(arrays[i], i + "");
			} else {
				maps.put(arrays[i], maps.get(arrays[i]) + "," + i);
				
			}
		}
		Set<Entry<Integer, String>> entrySet = maps.entrySet();
		for (Entry<Integer, String> entry : entrySet) {
			String value = entry.getValue();
			if (value.length() > 1) {
				System.out.println("重复的元素：" + entry.getKey() + ",出现的位置有：" + value);
			}
		}
	}
	
}
