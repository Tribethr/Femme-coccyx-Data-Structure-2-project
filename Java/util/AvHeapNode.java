package util;

public class AvHeapNode {
	int parent;
	int leftSon;
	int rigthSon;
	
	public AvHeapNode(int pParent, int pLeftSon, int pRightSon) {
		parent = pParent;
		leftSon = pLeftSon;
		rigthSon = pRightSon;
	}

	public int getParent() {
		return parent;
	}

	public int getLeftSon() {
		return leftSon;
	}

	public int getRigthSon() {
		return rigthSon;
	}
}
