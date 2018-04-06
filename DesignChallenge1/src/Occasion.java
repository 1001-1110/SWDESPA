
public abstract class Occasion {

	int id;
	private String info;
	private String colorString;
	
	public Occasion(String info, String colorString, int id) {
		this.info = info;
		this.colorString = colorString;
		this.id = id;
	}

	public String getInfo() {
		return info;
	}
	
	public int getID() {
		return id;
	}
	
	public String getColorString() {
		return colorString;
	}
	
}
