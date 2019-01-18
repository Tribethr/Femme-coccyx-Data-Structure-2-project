package imageManagement;

import java.util.ArrayList;

public class TagManager {
	private ArrayList<Tag> tags;
	private int index;
	private int[] maxForIndex;
	private int iterCount;
	
	public TagManager(ArrayList<Tag> tags) {
		if (tags.size() > 10) {
			this.tags = new ArrayList<Tag>();
			for (int i = 0; i < 10; i++) {
				this.tags.add(tags.get(i));
			}
		} else {
			this.tags = tags;
		}
		index = 0;
		iterCount = 0;
		maxForIndex = new int[tags.size()];
		for (int i = 0; i < maxForIndex.length; i++) {
			int n = (int) (tags.get(i).getConfidence() * 10);
			maxForIndex[i] = n == 0 ? 1 : n;
		}
	}
	
	public Tag actualTag() {
		return tags.get(index);
	}
	
	public Tag nextTag() {
		if(maxForIndex[index] == iterCount) {
			iterCount = 0;
			index++;
		}
		
		if(index == tags.size()) { 
			index = 0;
		}
		
		iterCount++;
		
		return tags.get(index);
	}
}