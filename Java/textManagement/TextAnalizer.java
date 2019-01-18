package textManagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import library.IConstants;


public class TextAnalizer implements IConstants{
	private ArrayList<ArrayList<ArrayList<String>>> regions;
	private int minLengthPerWord;
	private int maxLengthPerWord;
	private char lastChar;
	
	public TextAnalizer (String pDir, int pMinLengthPerWord, int pMaxLengthPerWord) throws IOException{
		minLengthPerWord = pMinLengthPerWord;
		maxLengthPerWord = pMaxLengthPerWord;
		regions = new ArrayList<>();
		for(int region = 0; region<NUMBER_OF_REGIONS; region++) {
			regions.add(new ArrayList<ArrayList<String>>());
		}
		divideText(pDir);
	}
	
	public void divideText(String pDir) {
		try {
			FileReader fileReader = new FileReader(pDir);   
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			for(int region = 0;(line = bufferedReader.readLine()) != null;) {
				if(++region == NUMBER_OF_REGIONS) {
					region = 0;
				}
				sortWords(line,region);
			}
		}catch(Exception e) {
			System.out.println("No pude leer el archivo");
		}
		
	}
	
	public void sortWords(String pLine, int pRegion) {
		String words[] = pLine.split(" ");
		ArrayList<String> acceptedWords = new ArrayList<>();
		for(String word: words) {
			if(word.length()> minLengthPerWord && word.length()< maxLengthPerWord) {
				lastChar = word.charAt(word.length()-1);
				if(lastChar == ',' || lastChar == '.') {
					acceptedWords.add(word.substring(0, word.length()-1).toLowerCase());
				}else {
					acceptedWords.add(word.toLowerCase());
				}
			}
		}
		regions.get(pRegion).add(acceptedWords);
	}
	
	public ArrayList<ArrayList<ArrayList<String>>> getRegions(){
		return regions;
	}

}
	