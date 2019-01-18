package imageManagement;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

import library.ImageSample;
import library.RegionCounter;
import library.WordContainer;
import textManagement.TextAnalizer;
import ui.UiCoccyx;
import util.GraphContainer;

public class ImageModifier {
	
	private String[] topTags;
	private String[] words;
	private ImageAnalizer imageAnalizer;
	private UiCoccyx ui;
	private GraphContainer[] MSTNodes;
	private BufferedImage image;
	private ImageSample actualSample;
	private WordContainer actualTag;
	private ConcurrentLinkedQueue<ArrayList<Integer>> roads;
	private TextAnalizer textAnalizer;
	
	public ImageModifier(ImageAnalizer pImageAnalizer, UiCoccyx pUi, GraphContainer[] pMSTNodes, ConcurrentLinkedQueue<ArrayList<Integer>> pRoads, TextAnalizer pTextAnalizer) {
		imageAnalizer = pImageAnalizer;
		textAnalizer = pTextAnalizer;
		ui = pUi;
		image = pUi.getImage();
		MSTNodes = pMSTNodes;
		roads = pRoads;
	}
	
	public void getTop10Tags() {
		
	}
	
	public void checkForBuilding() {
		ArrayList<Integer> road = roads.poll();
		System.out.println(road);
		while(roads.size() != 0) {
			actualSample = imageAnalizer.getSamplesById()[road.get(road.size()-1)];
			actualTag = textAnalizer.getWord(actualSample.getTag());
			System.out.println(actualSample.getTag());
			if(actualTag == null) {
				roads.poll();
				continue;
			}
			for(RegionCounter region : actualTag.getRegions()) {
				paintWithRoads(road,region.getRegion(), actualSample.getTag().substring(0, 1));
				paintWithMST(region.getRegion(), actualSample.getTag());
			}
			roads.poll();
		}
	}
	
	public int getAparitions(int pRegion) {
		if(actualTag !=null) {
			for(RegionCounter region : actualTag.getRegions()) {
				if(region.getRegion() == pRegion) {
					return region.getCounter();
				}
			}
		}
		return 0;
	}
	
	public void paintWithRoads(ArrayList<Integer> road, int pRegion, String pTag) {
		ImageSample actualSample;
		int minX;
		int yPosition;
		int aparitions;
		for(int node : road) {
			actualSample = imageAnalizer.getSamplesById()[node];
			if(actualSample.getRegion() == pRegion) {
				aparitions = getAparitions(pRegion);
				ui.changeColor(actualSample.getRGB());
				for(int[] actualCoordinates: actualSample.getCoordinates()) {	
					minX = actualCoordinates[0];
					yPosition = actualCoordinates[1];
					
					for(int aparitionCounter = 0;aparitionCounter<aparitions && minX<actualSample.getMaxXPosition();minX++, aparitionCounter++){
						ui.drawText(pTag, minX, yPosition);
					}
				}
			}
		}
		ui.repaint();
	}
	public void paintWithMST(int pRegion, String tag) {
		ImageSample actualSample;
		for(GraphContainer node : MSTNodes) {
			actualSample = imageAnalizer.getSamplesById()[node.getActual()];
			if(actualSample.getRegion() == pRegion && actualSample.getTag().equals(tag)) {
				Color actualColor = actualSample.getRGB();
				Color newColor = new Color(Math.abs((actualColor.getRed()-node.getDistanceFromRoot())%256), actualColor.getGreen(), Math.abs((actualColor.getBlue()+node.getDistanceFromRoot())%256));
				for(int[] actualCoordinates: actualSample.getCoordinates()) {
					image.setRGB(actualCoordinates[0], actualCoordinates[1], newColor.getRGB());
				}
			}
		}		
		ui.repaint();
	}
	
}
