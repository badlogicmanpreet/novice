package com.manpreet.gojava.dsa;

public class QueueFrom2Stacks {

	private ArrayStack inStack = new ArrayStack();
	private ArrayStack outStack = new ArrayStack();

	public static void main(String[] args) {
		QueueFrom2Stacks qStacks = new QueueFrom2Stacks();

		qStacks.enqueue("manpreet");
		qStacks.enqueue("java");
		qStacks.enqueue("geek");
		qStacks.dequeue();
		qStacks.dequeue();
		qStacks.dequeue();
	}

	private void enqueue(Object o) {
		inStack.push(o);
	}

	private Object dequeue() {
		if (outStack.isEmpty()) {
			while (!inStack.isEmpty()) {
				outStack.push(inStack.pop());
			}
		}
		String name = (String) outStack.pop();
		System.out.println(name);
		return name;
	}

}
