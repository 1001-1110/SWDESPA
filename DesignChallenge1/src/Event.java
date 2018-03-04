import java.awt.Color;

public class Event extends Occasion{

	String durationFrom;
	String durationTo;
	boolean isDone;
	
	public Event(String info, int month, int day, int year, Color color) {
		super(info, month, day, year, color);
	}

	public Event(String info, int month, int day, int year, String colorString) {
		super(info, month, day, year, colorString);
	}	
	
}