package com.manpreet.gojava.pattern.strategy;

public class SortingContext {

	public SortInterface sorter;

	public void sortDouble(double[] list, int low, int length) {
		sorter.sort(list, low, length);
	}

	public SortInterface getSorter() {
		return sorter;
	}

	public void setSorter(SortInterface sorter) {
		this.sorter = sorter;
	}

}
