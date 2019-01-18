package util;

import java.util.ArrayList;

public class PriorityQueue<T> {
	ArrayList<Queue<T>> queues = new ArrayList<Queue<T>>();
	
	public boolean isEmpty() {
		return queues.size() == 0;
	}
	
	public void enqueueLR(T pData, int pPriority) {
		Queue<T> actual;
		for(int i = 0; i<queues.size() ; i++) {
			actual = queues.get(i);
			if(pPriority < actual.getPriority()) {
				queues.add(i, new Queue<T>(pData, pPriority));
				return;
			}else if(pPriority == actual.getPriority()) {
				actual.enqueue(pData);
				return;
			}
		}
		queues.add(new Queue<T>(pData, pPriority));
	}
	
	public void enqueueRL(T pData, int pPriority) {
		Queue<T> actual;
		for(int i = queues.size()-1; i>-1; i--) {
			actual = queues.get(i);
			if(pPriority > actual.getPriority()) {
				queues.add(i+1, new Queue<T>(pData, pPriority));
				return;
			}else if(pPriority == actual.getPriority()) {
				actual.enqueue(pData);
				return;
			}
		}
		queues.add(0,new Queue<T>(pData, pPriority));
	}
	
	public void clear() {
		queues.clear();
	}
	
	public T dequeue() {
		T actual = queues.get(0).dequeue();
		if(queues.get(0).isEmpty()) {
			queues.remove(0);
		}
		return actual;
	}
}
