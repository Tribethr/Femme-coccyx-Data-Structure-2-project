package util;

public class Avl implements AvHeap{
	AvHeapNode []pData;
	public Avl(int pSize) {
		pData = new AvHeapNode[pSize];
	}
	
	public void insert(int pIndex, int pParent, int pLeftSon, int pRightSon) {
		pData[pIndex] = new AvHeapNode(pParent, pLeftSon, pRightSon);
	}
	
	public int getLeftSon(int pId) {
	    return pData[pId].getLeftSon();
	}
	
	public int getRightSon(int pId) {
	    return pData[pId].getRigthSon();
	}
	
	public int getParent(int pId) {
		return pData[pId].getParent();
	}
}
