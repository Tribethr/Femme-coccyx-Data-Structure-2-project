package util;

public class GraphContainer {
	int previous;
	int previousIsFromAvl;
	int actual;
	int actualIsFromAvl;
	int weight;
	int distanceFromRoot;

	public GraphContainer(int pPrevious, int pPreviousIsFromAvl, int pActual, int pActualIsFromAvl, int pWeight){
		previous = pPrevious;
		previousIsFromAvl = pPreviousIsFromAvl;
	    actual = pActual;
		actualIsFromAvl = pActualIsFromAvl;
		weight = pWeight;
	}
	
	public GraphContainer(int pPrevious, int pPreviousIsFromAvl, int pActual, int pActualIsFromAvl, int pWeight, int pDistance){
		previous = pPrevious;
		previousIsFromAvl = pPreviousIsFromAvl;
	    actual = pActual;
		actualIsFromAvl = pActualIsFromAvl;
		weight = pWeight;
		distanceFromRoot = pDistance;
	}
	
	public int getPrevious(){
		return previous;
	}
	public int isPreviousFromAvl(){
		return previousIsFromAvl;
	}
	public int getActual(){
		return actual;
	}
	
	public int getDistanceFromRoot() {
		return distanceFromRoot;
	}
	
	public int isActualFromAvl(){
		return actualIsFromAvl;
	}
	public int getWeight(){
		return weight;
	}

}
