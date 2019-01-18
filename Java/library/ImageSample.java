package library;

import java.awt.Color;
import java.util.ArrayList;

public class ImageSample {
	private ArrayList<int[]> coordinates;
	private int repetitions;
	private int minXPosition;
	private int maxXPosition;
	private Color rgb;
	private int id;
	private int region;
	private String tag;
	
	public ImageSample (int[] pCoordinates, int pRepetitions, int pId, Color pRGB, int pMinXPosition, int pMaxXPosition, int pRegion, String pTag) {
		coordinates = new ArrayList<int[]>();
		coordinates.add(pCoordinates);
		repetitions = pRepetitions;
		minXPosition = pMinXPosition;
		maxXPosition = pMaxXPosition;
		rgb = pRGB;
		id = pId;
		region = pRegion;
		tag = pTag;
	}
	
	public void addCoordinates(int[]pCoordinates) {
		coordinates.add(pCoordinates);
	}
	
	public ArrayList<int[]> getCoordinates(){
		return coordinates;
	}
	
	public Color getRGB() {
		return rgb;
	}
	
	public int getRegion() {
		return region;
	}
	
	public String getTag() {
		return tag;
	}
	
	public int getRepetitions() {
		return repetitions;
	}

	public int getId() {
		return id;
	}
	
	public void addRepetitions() {
		repetitions++;
	}

	public int getMinXPosition() {
		return minXPosition;
	}

	public int getMaxXPosition() {
		return maxXPosition;
	}

	@Override
	public String toString() {
		return "ImageSample [repetitions=" + repetitions + ", minXPosition=" + minXPosition + ", maxXPosition="
				+ maxXPosition + ", rgb=" + rgb + ", id=" + id + ", region=" + region + ", tag=" + tag + "]";
	}
	
}
