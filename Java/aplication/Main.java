package aplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;

import dataTransfer.CJMessenger;
import imageManagement.AzureCommunicator;
import imageManagement.ImageAnalizer;
import imageManagement.ImageModifier;
import imageManagement.Tag;
import imageManagement.TagManager;
import textManagement.TextAnalizer;
import ui.UiCoccyx;
import util.AvHeap;
import util.Avl;
import util.HashRoads;
import util.Heap;
import util.Mst;
import util.NodeSort;

public class Main {
	@SuppressWarnings("unused")
	public static void main(String args[]) throws IOException {
		long tiempoInicial = System.currentTimeMillis();
		System.out.println("Getting tags from azure...");
		AzureCommunicator tagsReciever = new AzureCommunicator();
		TagManager tagManager = new TagManager(tagsReciever.getTags("https://www.dropbox.com/s/nrj0950lq5o0uop/TestImage.jpg?dl=0"));
		System.out.println("Tags recieved");
		System.out.println("Analizing text...");
		TextAnalizer textAnalizer = new TextAnalizer("test.txt",3,10);
		System.out.println("Text analized");
		System.out.println("Analizing image...");
		ImageAnalizer imageAnalizer = new ImageAnalizer(tagManager);
		System.out.println("Image analized");
		UiCoccyx ui = new UiCoccyx(imageAnalizer.getImage());
		CJMessenger messenger = new CJMessenger();
		System.out.println("Conecting to c++...");
		messenger.doConnection();
		int repetitions = imageAnalizer.getMaxRepetitions();
		System.out.println("Repetitions: "+repetitions);
		messenger.sendData(repetitions);
		Heap heap = new Heap(repetitions+1);
		Avl avl = new Avl(repetitions);
		AvHeap[] avHeap = {heap,avl};
		int[] weights = new int[repetitions];
		NodeSort [] sortedNodes = new NodeSort[repetitions];
		int actualNode;
		int actualWeight;
		System.out.println("Recieving info from c++...");
		for(int index = 0; index<repetitions; index++) {
			actualNode = messenger.waitForData();
			heap.insert(actualNode);
			avl.insert(actualNode, messenger.waitForData(), messenger.waitForData(), messenger.waitForData());
			actualWeight = messenger.waitForData();
			weights[index] = actualWeight;
			sortedNodes[index] = new NodeSort(index, actualWeight);
		}
		System.out.println("Info recieved");
		Arrays.sort(sortedNodes);
		heap.activate();
		int lessWeigthIndex = 0;
		for(int index = 0; index<sortedNodes.length; index++) {
			if(sortedNodes[index].getWeight()>0) {
				lessWeigthIndex = index;
				break;
			}
		}
		System.out.println("Less weight: "+sortedNodes[lessWeigthIndex].getWeight());
		int last = sortedNodes.length-1;
		repetitions = last - repetitions/10;
		System.out.println("Creating mst...");
		Mst mst = new Mst(avHeap, 2, weights);
		System.out.println("Mst created");
		ConcurrentLinkedQueue<ArrayList<Integer>> roads = new ConcurrentLinkedQueue<ArrayList<Integer>>();
		ConcurrentLinkedQueue<int[]> petitions = new ConcurrentLinkedQueue<int[]>();
		System.out.println("Generating petitions...");
		for(int first = lessWeigthIndex; last>repetitions; first++,last--) {
			petitions.add( new int[] {sortedNodes[last].getNode(), sortedNodes[first].getNode()});
		}
		System.out.println("Petitions ready");
		System.out.println("Generating roads...");
		ImageModifier modifier = new ImageModifier(imageAnalizer,ui,mst.getNodes(),roads,textAnalizer);
		for(int index = 0; index<10; index++) {	
			HashRoads roadGenerator = new HashRoads(avHeap, weights, roads, petitions,modifier);
			roadGenerator.start();
			roadGenerator.setName(String.valueOf(index));
		}
		System.out.println("Modifying image...");
		System.out.println("End");
		System.out.println("Tiempo: "+(System.currentTimeMillis()-tiempoInicial));
	}
}
