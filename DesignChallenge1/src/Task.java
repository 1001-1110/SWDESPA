import java.awt.Color;

public class Task extends Occasion{
	
	String durationFrom;
	String durationTo;
	boolean isDone;
	
	public Task(String info, int month, int day, int year, Color color) {
		super(info, month, day, year, color);
	}

	public Task(String info, int month, int day, int year, String colorString) {
		super(info, month, day, year, colorString);
	}	
	
	
}
