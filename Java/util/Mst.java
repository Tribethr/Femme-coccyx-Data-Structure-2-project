package util;


import util.PriorityQueue;

public class Mst {
	AvHeap[] avHeap;
	int[] weights;
	PriorityQueue<GraphContainer> queue;
	GraphContainer[] nodes;
	int size;
	boolean[][] bothCheckedElements;
	
	public Mst(AvHeap []pAvHeap, int pOrigin, int[] pWeights) {
		avHeap = pAvHeap;
		weights = pWeights;
		queue = new PriorityQueue<GraphContainer>();
		bothCheckedElements = new boolean[2][weights.length];
		bothCheckedElements[0] = new boolean[weights.length];
		bothCheckedElements[1] = new boolean[weights.length];
		nodes = new GraphContainer[weights.length*2];
		init(pOrigin);
	}
	
	public GraphContainer[] getNodes() {
		return nodes;
	}
	
	private void init(int pOrigin) {
		GraphContainer container = new GraphContainer(0,1,pOrigin,1,0);
	    queue.enqueueLR(container,0);
	    int parent = -1;
	    int leftSon = -1;
	    int rightSon = -1;
	    int stopTime = weights.length*2;
	    int actualDataStructure = 0;
	    int actualDistance;
	    int actualWeight;
	    for(int condition = 0; condition<stopTime; condition++){
	        container = queue.dequeue();
	        actualDataStructure = container.isActualFromAvl();
	        bothCheckedElements[actualDataStructure][container.getActual()] = true;
	        parent = avHeap[actualDataStructure].getParent(container.getActual());
		    leftSon = avHeap[actualDataStructure].getLeftSon(container.getActual());
	        rightSon = avHeap[actualDataStructure].getRightSon(container.getActual());
	        actualDistance = container.getDistanceFromRoot()+1;
	        
	        if(parent != -1){
	            if(bothCheckedElements[actualDataStructure][parent]){}
	            else{
	                queue.enqueueLR(new GraphContainer(container.getActual(),container.isActualFromAvl(),parent,actualDataStructure,1,actualDistance),1) ;
	                //System.out.println("Parent: "+parent);
	            }
	        }
	        if(leftSon != -1){
	            if(bothCheckedElements[actualDataStructure][leftSon]){}
	            else{
	                queue.enqueueLR(new GraphContainer(container.getActual(),container.isActualFromAvl(),leftSon,actualDataStructure,1,actualDistance),1);
	                //System.out.println("LeftSon: "+leftSon);
	            }
	        }
	        if(rightSon != -1){
	            if(bothCheckedElements[actualDataStructure][rightSon]){}
	            else{
	                queue.enqueueLR(new GraphContainer(container.getActual(),container.isActualFromAvl(),rightSon,actualDataStructure,1,actualDistance),1);
	                //System.out.println("RightSon: "+rightSon);
	            }
	        }
	        if(!bothCheckedElements[actualDataStructure==1?0:1][container.getActual()]){
	        	actualWeight = weights[container.getActual()];
	        	actualDistance = actualDistance-1+actualWeight;
	            queue.enqueueLR(new GraphContainer(container.getActual(),container.isActualFromAvl(),container.getActual(),actualDataStructure==1?0:1,actualWeight,actualDistance),actualWeight);
	           // System.out.println(actualDataStructure == 1?"Monticulo: ":"Avl: "+container.getActual());
	        }
	        //System.out.println("Soy "+container.getActual()+(container.isActualFromAvl()==1?" Avl":" Monticulo")+" y vine de "+(container.isPreviousFromAvl()==1?"Avl ":"Monticulo ")+container.getPrevious()+" con un peso de "+container.getWeight());
	        //System.out.println(container.getActual());
	        nodes[size++] = container;
	    }
	}
}
