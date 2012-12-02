package com.manpreet.gojava.dsa;

public class ArrayStack implements Stack {

	private Object[] array;
	private int topOfStack;
	private static final int DEFAULT_SIZE = 10;

	public ArrayStack() {
		array = new Object[DEFAULT_SIZE];
		topOfStack = -1;
	}

	@Override
	public void push(Object o) {
		// TODO Auto-generated method stub
		if (!isFull()) {
			array[++topOfStack] = o;
		}
	}

	@Override
	public Object pop() {
		// TODO Auto-generated method stub
		if (!isEmpty())
			return (array[topOfStack--]);
		else
			return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (topOfStack == -1);
	}

	@Override
	public void makeEmpty() {
		// TODO Auto-generated method stub
		topOfStack = -1;
	}

	private boolean isFull() {
		return (topOfStack == array.length);
	}

	public static void main(String[] args) {
		ArrayStack arrayStack = new ArrayStack();
		arrayStack.push("manpreet");
		String name = (String) arrayStack.pop();
		System.out.println(name);
	}
}
