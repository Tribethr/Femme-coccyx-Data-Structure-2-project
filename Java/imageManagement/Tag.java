package imageManagement;

public class Tag {
	private String tag;
	private double confidence;
	
	public Tag(String tag, Double confidence) {
		this.tag = tag;
		this.confidence = confidence;
	}
	
	public String getTag() {
		return tag;
	}
	public double getConfidence() {
		return confidence;
	}
	
	public String toString() {
		return "(tag: " + tag + ", confidence: " + confidence + ")";
	}
}