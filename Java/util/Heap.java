package util;

public class Heap implements AvHeap{
	int[] data;
	AvHeapNode []nodes;
	public Heap(int pSize){
		data = new int[pSize];
		data[0] = -1;
	}
	
	public void activate() {
		nodes = new AvHeapNode[data.length];
		for(int index = 0; index<data.length; index++) {
			nodes[index] = new AvHeapNode(getParentAux(index), getLeftSonAux(index), getRightSonAux(index));
		}
	}
	
	public int getLeftSon(int pId) {
		return nodes[pId].getLeftSon();
	}
	
	public int getRightSon(int pId) {
		return nodes[pId].getRigthSon();
	}
	
	public int getParent(int pId) {
		return nodes[pId].getParent();
	}
	
	public void insert(int pValue) {
		data[pValue] = pValue;
	}
	
	private int getLeftSonAux(int pId) {
		int value = 2*(pId+1);
	    if(value>data.length){
	        return -1;
	    }
	    return data[value-1];
	}
	
	private int getRightSonAux(int pId) {
		int value = 2*(pId+1)+1;
	    if(value>data.length){
	        return -1;
	    }
	    return data[value-1];
	}
	
	private int getParentAux(int pId) {
		if(pId == 0) {
			return -1;
		}
		return data[(++pId/2)-1];
	}
}
