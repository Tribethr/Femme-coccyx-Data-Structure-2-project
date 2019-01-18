package util;

import java.util.LinkedList;

public class Queue<T> {
	LinkedList<T> list;
	int priority;
	
	public Queue() {
		list = new LinkedList<T>();
	}
	
	public Queue(T pData, int pPriority){
		priority = pPriority;
		list = new LinkedList<T>();
		enqueue(pData);
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void enqueue(T pData) {
		list.add(pData);
	}
	public T dequeue() {
		return list.removeFirst();
	}
	public boolean isEmpty() {
		return list.size() == 0;
	}
}
