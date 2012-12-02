package com.manpreet.gojava.pattern.strategy;

public class SortingClient {

	public static void main(String[] args) {
		double[] list = { 12, 14, 3, 99, 200, 45, 60, 79, 1, 2 };

		SortingContext context = new SortingContext();
		context.setSorter(new QuickSort());

		context.sortDouble(list, 0, list.length - 1);
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i]);
		}
	}

}
