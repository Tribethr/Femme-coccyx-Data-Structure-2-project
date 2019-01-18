package util;

import java.util.Comparator;

public class NodeSort implements Comparable<NodeSort>{
	private int node;
	private int weight;
	
	public NodeSort(int pNode, int pWeight) {
		node = pNode;
		weight = pWeight;
	}

	public int compareTo( NodeSort pValue2) {
		if(weight == pValue2.getWeight()) {
			return 0;
		}else if(weight > pValue2.getWeight()) {
			return 1;
		}else {
			return -1;
		}
	}

	public int getNode() {
		return node;
	}

	public int getWeight() {
		return weight;
	}
	
}
