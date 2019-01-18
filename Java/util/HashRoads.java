package util;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import imageManagement.ImageModifier;

public class HashRoads extends Thread{
	ConcurrentLinkedQueue< ArrayList<Integer>> roads;
	ConcurrentLinkedQueue<int[]> petitions;
	ImageModifier modifier;
	AvHeap[] avHeap;
	int[] weights;
	PriorityQueue<GraphContainer> queue;
	boolean[][] bothCheckedElements;
	GraphContainer [][] containers;
	GraphContainer[] cleaner;
	int pointer;
	int parent = -1;
    int leftSon = -1;
    int rightSon = -1;
    int stopTime;
    int actualDataStructure = 0;
    int actualWeight = 0;
    
	public HashRoads(AvHeap []pAvHeap, int[] pWeights, ConcurrentLinkedQueue<ArrayList<Integer>> pRoads, ConcurrentLinkedQueue<int[]> pPetitions,ImageModifier pModifier) {
		roads = pRoads;
		petitions = pPetitions;
		avHeap = pAvHeap;
		weights = pWeights;
		modifier = pModifier;
		stopTime = weights.length*2;
		queue = new PriorityQueue<GraphContainer>();
		bothCheckedElements = new boolean[2][weights.length];
		bothCheckedElements[0] = new boolean[weights.length];
		bothCheckedElements[1] = new boolean[weights.length];
		cleaner = new GraphContainer[weights.length*2];
		containers = new GraphContainer[2][];
		containers[0] = new GraphContainer[weights.length];
		containers[1] = new GraphContainer[weights.length];
	}
	
	public ConcurrentLinkedQueue<ArrayList<Integer>> getRoads() {
		return roads;
	}
	
	public void createRoad(int pOrigin, int pDestiny) {
		ArrayList<Integer> road = new ArrayList<Integer>();
		GraphContainer container = new GraphContainer(pOrigin,1,pOrigin,1,0);
		containers[1][pOrigin] = container;
	    queue.enqueueRL(container,0);
	    parent = -1;
	    leftSon = -1;
	    rightSon = -1;
	    stopTime = weights.length*2;
	    actualDataStructure = 0;
	    actualWeight = 0;
	    for(int condition = 0; condition<stopTime; condition++){
	        container = queue.dequeue();
	        actualDataStructure = container.isActualFromAvl();
	        bothCheckedElements[actualDataStructure][container.getActual()] = true;
	        parent = avHeap[actualDataStructure].getParent(container.getActual());
		    leftSon = avHeap[actualDataStructure].getLeftSon(container.getActual());
	        rightSon = avHeap[actualDataStructure].getRightSon(container.getActual());
	        actualWeight = container.getWeight()+1;
	        if(parent != -1){
	            if(bothCheckedElements[actualDataStructure][parent]){}
	            else{
	            	if(parent == pDestiny && actualDataStructure == 1) {
	            		containers[actualDataStructure][container.getActual()] = container;
	            		cleaner[pointer++] = container;
	            		container = new GraphContainer(container.getActual(), container.isActualFromAvl(), parent, actualDataStructure, actualWeight);
	            		break;
	            	}
	                queue.enqueueRL(new GraphContainer(container.getActual(),container.isActualFromAvl(),parent,actualDataStructure,actualWeight),actualWeight) ;
	                //System.out.println("Parent: "+parent);
	            }
	        }
	        if(leftSon != -1){
	            if(bothCheckedElements[actualDataStructure][leftSon]){}
	            else{
	            	if(leftSon == pDestiny && actualDataStructure == 1) {
	            		containers[actualDataStructure][container.getActual()] = container;
	            		cleaner[pointer++] = container;
	            		container = new GraphContainer(container.getActual(), container.isActualFromAvl(), leftSon, actualDataStructure, actualWeight);
	            		break;
	            	}
	                queue.enqueueRL(new GraphContainer(container.getActual(),container.isActualFromAvl(),leftSon,actualDataStructure,actualWeight),actualWeight);
	                //System.out.println("LeftSon: "+leftSon);
	            }
	        }
	        if(rightSon != -1){
	            if(bothCheckedElements[actualDataStructure][rightSon]){}
	            else{
	            	if(rightSon == pDestiny && actualDataStructure == 1) {
	            		containers[actualDataStructure][container.getActual()] = container;
	            		cleaner[pointer++] = container;
	            		container = new GraphContainer(container.getActual(), container.isActualFromAvl(), rightSon, actualDataStructure, actualWeight);
	            		break;
	            	}
	                queue.enqueueRL(new GraphContainer(container.getActual(),container.isActualFromAvl(),rightSon,actualDataStructure,actualWeight),actualWeight);
	                //System.out.println("RightSon: "+rightSon);
	            }
	        }
	        if(!bothCheckedElements[actualDataStructure==1?0:1][container.getActual()]){
	        	if(container.getActual() == pDestiny && actualDataStructure == 0) {
	        		containers[actualDataStructure][container.getActual()] = container;
	        		cleaner[pointer++] = container;
	        		container = new GraphContainer(container.getActual(), container.isActualFromAvl(), container.getActual(), actualDataStructure==1?0:1, actualWeight);
	        		break;
            	}
	        	actualWeight = actualWeight-1+weights[container.getActual()];
	            queue.enqueueRL(new GraphContainer(container.getActual(),container.isActualFromAvl(),container.getActual(),actualDataStructure==1?0:1,actualWeight),actualWeight);
	            //System.out.println((actualDataStructure == 1?"Monticulo: ":"Avl: ")+container.getActual());
	        }
	        //System.out.println("Soy "+container.getActual()+(container.isActualFromAvl()==1?" Avl":" Monticulo")+" y vine de "+(container.isPreviousFromAvl()==1?"Avl ":"Monticulo ")+container.getPrevious()+" con un peso de "+container.getWeight());
	        //System.out.println(container.getActual());
	        containers[actualDataStructure][container.getActual()] = container;
	        cleaner[pointer++] = container;
	    }
	    //System.out.println(container.getActual()+(container.isActualFromAvl()==1?" Avl":" Monticulo"));
	    road.add(container.getActual());
	    while(container.getActual() != pOrigin) {
	    	container = containers[container.isPreviousFromAvl()][container.getPrevious()];
	    	//System.out.println(container.getActual()+(container.isActualFromAvl()==1?" Avl":" Monticulo"));
	    	road.add(container.getActual());
	    }
	    roads.add(road);
	    System.out.println("Terminé"+this.getName()+" "+roads.size());
	    if(roads.size() > 1) {
	    	modifier.checkForBuilding();
	    }
	    clear();
	    queue.clear();
	    pointer = 0;
	}
	
	private void clear() {
		GraphContainer actualContainer;
		for(int index = 0; index<pointer; index++) {
			actualContainer = cleaner[index];
			containers[actualContainer.isActualFromAvl()][actualContainer.getActual()] = null;
			bothCheckedElements[actualContainer.isActualFromAvl()][actualContainer.getActual()] = false;
		}
	}
	
	@Override
	public void run() {
		int [] actualPetition = petitions.poll();
		while(actualPetition != null) {
			createRoad(actualPetition[0], actualPetition[1]);
			actualPetition = petitions.poll();
		}
	}
	
}
