package imageManagement;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;

import org.json.simple.JSONObject;

import library.IConstants;
import library.ImageSample;

public class ImageAnalizer implements IConstants{
	private BufferedImage image;
	private int imageWidth;
	private int imageHeight;
	private int xMovingFactor;
	private int yMovingFactor;
	private ArrayList<HashMap<Integer, ImageSample>> samples;
	private ImageSample[] samplesById;
	private ArrayList<HashMap<String, ArrayList<Integer>>> samplesByTag;
	private int maxRepetitions;
	private float percentageExtraction;
	private Random random;
	private int xRegionLowerLimit;
	private int yRegionLowerLimit;
	private int pixelsPerRegion;
	private int regionSize;
	private int actualId;
	private TagManager tagManager;
	
	public ImageAnalizer(TagManager pTagManager) {
		tagManager = pTagManager;
		random = new Random();
		samples = new ArrayList<HashMap<Integer, ImageSample>>();
		samplesByTag = new ArrayList<HashMap<String, ArrayList<Integer>>>();
		for(int region = 0; region<NUMBER_OF_REGIONS; region++) {
			samples.add(new HashMap<Integer,ImageSample>());
			samplesByTag.add(new HashMap<String,ArrayList<Integer>>());
		}
		regionSize = (int)Math.sqrt(NUMBER_OF_REGIONS);
		System.out.println("Region size: "+regionSize);
		percentageExtraction = 10+random.nextInt(6);
		loadImage();
		imageHeight = image.getHeight();
		imageWidth = image.getWidth();
		divideImage();
		pixelsPerRegion = (int) ((percentageExtraction/100)*xMovingFactor*yMovingFactor);
		samplesById = new ImageSample[pixelsPerRegion*NUMBER_OF_REGIONS];
		extractSamples();
		//divideRegions();
		System.out.println("Done!");
	}
	
	public void loadImage() {
		try {
			image = ImageIO.read(new File("TestImage.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ImageSample[] getSamplesById() {
		return samplesById;
	}
	
	public int getMaxRepetitions() {
		return maxRepetitions;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public ArrayList<HashMap<Integer, ImageSample>> getSamples(){
		return samples;
	}
	
	public ArrayList<ImageSample> getSamplesByTag(int pRegion, String pTag) {
		ArrayList<ImageSample> data = new ArrayList<ImageSample>();
		ArrayList<Integer> keys = samplesByTag.get(pRegion).get(pTag);
		for(int index = 0; index<keys.size(); index++) {
			data.add(samples.get(pRegion).get(keys.get(index)));
		}
		return data;
	}
	
	private void divideImage() {
		xMovingFactor = imageWidth/regionSize;
		yMovingFactor = imageHeight/regionSize;
	}
	
	private void extractSamples() {
		for(int region = 0, row = 1; region<NUMBER_OF_REGIONS; region++, row++) {
			for(int pixels = 0; pixels<pixelsPerRegion; pixels++) {
				addRandomPixel(region);
			}
			maxRepetitions += samples.get(region).size();
			xRegionLowerLimit += xMovingFactor;
			if(row == regionSize) {
				xRegionLowerLimit = 0;
				yRegionLowerLimit += yMovingFactor;
				row = 0;
			}
		}
	}
	
	private void divideRegions() {
		for(int row = yMovingFactor, count = 1; count<regionSize; row+= yMovingFactor) {
			for(int line = 0; line<imageWidth; line++) {
				image.setRGB(line, row, 0);
			}
			count++;
		}
		for(int column = xMovingFactor, count = 1; count<regionSize; column+= xMovingFactor) {
			for(int line = 0; line<imageWidth; line++) {
				image.setRGB(column, line, 0);
			}
			count++;
		}
	}
	
	private void addRandomPixel(int pRegion) {
		int coordinates[] = {xRegionLowerLimit+random.nextInt(xMovingFactor),yRegionLowerLimit+random.nextInt(yMovingFactor)};
		int pixel = image.getRGB(coordinates[0],coordinates[1]);
		if(samples.get(pRegion).containsKey(pixel)){
			ImageSample sample = samples.get(pRegion).get(pixel);
			sample.addRepetitions();
			sample.addCoordinates(coordinates);
		}else {
			ImageSample actualSample = new ImageSample(coordinates, 1, actualId,new Color(pixel),xRegionLowerLimit,xRegionLowerLimit+xMovingFactor,pRegion, tagManager.actualTag().getTag());
			samplesById[actualId++] = actualSample;
			samples.get(pRegion).put(pixel, actualSample);
			if(samplesByTag.get(pRegion).containsKey(tagManager.actualTag().getTag())) {
				samplesByTag.get(pRegion).get(tagManager.actualTag().getTag()).add(pixel);
			}else {
				ArrayList<Integer> newTag = new ArrayList<Integer>();
				newTag.add(pixel);
				samplesByTag.get(pRegion).put(tagManager.actualTag().getTag(), newTag);
			}
			tagManager.nextTag();
		}
	}
}
