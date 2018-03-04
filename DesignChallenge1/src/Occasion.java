import java.awt.Color;

public abstract class Occasion {

	private String info;
	private Color color;
	private String colorString;
	
	public Occasion(String info, Color color) {
		this.info = info;
		this.color = color;
	}
	
	public Occasion(String info, String colorString) {
		this.info = info;
		colorString = colorString.toLowerCase();
		
		if(colorString.equals("blue"))
			this.color = Color.BLUE;
		else if(colorString.equals("gray"))
			this.color = Color.GRAY;
		else if(colorString.equals("green"))
			this.color = Color.GREEN;
		
		this.colorString = colorString;
	}

	public String getInfo() {
		return info;
	}
	
	public Color getColor() {
		return color;
	}

	public String getColorString() {
			return colorString;
	}	
	
}
