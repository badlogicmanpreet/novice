package com.manpreet.gojava.pattern.strategy;

public class QuickSort implements SortInterface {

	public static void main(String[] args) {
		double[] list = { 12, 14, 3, 99, 200, 45, 60, 79, 1, 2 };

		new QuickSort().sort(list, 0, list.length - 1);
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i]);
		}
	}

	@Override
	public void sort(double[] list, int low, int len) {
		// TODO Auto-generated method stub
		int lo = low;
		int hi = len;
		int mid = (int) list[(lo + hi) / 2];

		if (lo >= len) {
			return;
		}

		while (lo < hi) {
			while (lo < hi && list[lo] < mid) {
				lo++;
			}
			while (lo < hi && list[hi] > mid) {
				hi--;
			}
			if (lo < hi) {
				int temp = (int) list[lo];
				list[lo] = list[hi];
				list[hi] = temp;
			}
		}
//		if (hi < lo) {
//			int temp = hi;
//			hi = lo;
//			lo = temp;
//		}
		sort(list, low, lo);
		sort(list, lo + 1, len);
	}
}
