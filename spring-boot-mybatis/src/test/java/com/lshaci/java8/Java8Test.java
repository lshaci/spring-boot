package com.lshaci.java8;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.DoubleFunction;

import org.junit.Test;

public class Java8Test {


	@Test
	public void testName1() throws Exception {
		List<String> str = Arrays.asList("a", "b", "A", "B");
		str.sort(String::compareToIgnoreCase);
		System.out.println(str);
	}
	
	public double integrate(DoubleFunction<Double> f, double a, double b) {
		return (f.apply(a) + f.apply(b)) * (b - a) / 2.0;
	}

	@Test
	public void testName2() throws Exception {
		DoubleFunction<Double> f = x -> x + 10;

		double d = integrate(f, 9, 7);
		System.out.println(d);
	}

	@Test
	public void testListFiles() throws Exception {
		File[] listFiles = new File("E:/a").listFiles(File::isHidden);
		Arrays.asList(listFiles).forEach(e -> System.out.println(e.getName()));
		// 1499664445000
		Date date = new Date(1499684400000L);
		System.out.println(date);
	}

	@Test
	public void testForEach() throws Exception {
		Arrays.asList("a", "b", "d").forEach(e -> System.out.println(e));
	}

	@Test
	public void testReturn() throws Exception {
		Arrays.asList("a", "b", "d").sort((e1, e2) -> {
			int result = e1.compareTo(e2);
			return result;
		});
	}

	@Test
	public void testName() throws Exception {
		Thread thread = new Thread(this::doSometing);
		thread.start();
	}

	public void doSometing() {
		System.out.println("=============");
	}
}
