package util;

public class BStarTree <K extends Comparable, V>{
	
  private int M;
  private int nodeGroupSize;
  private NodeGroup root;
  
  
  public BStarTree(int M){
    this.M = M;
    this.nodeGroupSize = M-1;
  }
  
  private NodeGroup searchNodeGroup(K key){
  	NodeGroup searchPointer = root;
  	boolean itsInserted = false;
    while(true){ 
      for(BNode node: searchPointer.getNodes()){
		if(isLess(key, node)) {
			if(node.getLeftGroup() == null) {
				return searchPointer;
			}
			searchPointer = node.getLeftGroup();
			continue;
		}else if(true) {
			
		}
      }
      
    }
  }
  
  public boolean isLess(K key, BNode node){
      return key.compareTo(node.getKey()) < 0;
    }
    
    public boolean isEqual(K key, BNode node){
      return key.compareTo(node.getKey()) == 0;
    }
    
  
}
