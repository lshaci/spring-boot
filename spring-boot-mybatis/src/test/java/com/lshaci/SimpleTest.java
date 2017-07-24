package com.lshaci;

import java.util.Arrays;

public class SimpleTest {
	
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
	
}
