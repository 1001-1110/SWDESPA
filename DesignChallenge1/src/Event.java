import java.awt.Color;

public class Event {

	private int month;
	private int day;
	private int year;
	private String info;
	private Color color;
	private String colorString;
	
	public Event(String info, int month, int day, int year, Color color) {
		this.month = month;
		this.day = day;
		this.year = year;
		this.info = info;
		this.color = color;
	}
	
	public Event(String info, int month, int day, int year, String colorString) {
		this.month = month;
		this.day = day;
		this.year = year;
		this.info = info;
		colorString = colorString.toLowerCase();
		
		if(colorString.equals("blue"))
			this.color = Color.BLUE;
		else if(colorString.equals("red"))
			this.color = Color.RED;
		else if(colorString.equals("green"))
			this.color = Color.GREEN;
		
		this.colorString = colorString;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getDay() {
		return day;
	}

	public int getYear() {
		return year;
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
