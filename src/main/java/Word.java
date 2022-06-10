
public class Word {
	private String info;
	private int frequency;
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public void incrementFrequency() {
		this.frequency++;
	}

}
