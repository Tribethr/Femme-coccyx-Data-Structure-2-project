package util;

public class BNode<K extends Comparable, V> {
    private K key;
    private V value;
    private NodeGroup leftGroup;
    private NodeGroup rightGroup;
    
    public BNode(K key, V value){
    	this.key = key;
    	this.value = value;
    }
    
    public K getKey() {return key;}
    public V getValue() {return value;}
    public NodeGroup getLeftGroup(){return leftGroup;}
    public NodeGroup getRightGroup() {return rightGroup;}
    
    
  }