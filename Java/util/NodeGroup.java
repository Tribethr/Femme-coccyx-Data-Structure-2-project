package util;

public class NodeGroup{
    private BNode[] nodes;
    private NodeGroup parent;
    private BNode first;
    private BNode last;
    private int nodeGroupSize;
    private int actualNode;
    private boolean lowerStruct;
    
    public NodeGroup(NodeGroup parent, int nodeGroupSize){
    	this.parent = parent;
    	this.nodeGroupSize = nodeGroupSize;
    	lowerStruct = true;
    	nodes = new BNode[nodeGroupSize]; 
    }
    
    public BNode[] getNodes() {
    	return nodes;
    }
    
    public NodeGroup getParent() {
    	return parent;
    }
    
    public boolean isFLoor() {
    	return lowerStruct;
    }
    
    public void insertNode(BNode node) {
    	
    }
    
    public boolean isFull(){
      return actualNode == nodeGroupSize;
    }
    
    public BNode getFirst() {
    	return first;
    }
    
    public BNode getLast() {
    	return last;
    }
    
}