package com.manpreet.gojava.pattern.strategy;

public class BubbleSort implements SortInterface {

	public static void main(String[] args) {
		double[] list = { 12, 14, 3, 99, 200, 45, 60, 79, 1, 2 };

		new BubbleSort().sort(list, 0, list.length - 1);
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i]);
		}
	}

	@Override
	public void sort(double[] list, int low, int length) {
		// TODO Auto-generated method stub
		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list.length; j++) {
				if (list[i] < list[j]) {
					int temp = (int) list[j];
					list[j] = list[i];
					list[i] = temp;
				}
			}
		}
	}

}
