import java.util.ArrayList;

public interface CalendarControl {
	
	public abstract void attachModel(CalendarModel cm);
	
	public abstract void addOccasion(String info, String date, String durationFrom, String durationTo, String color, boolean isEvent, boolean isTask);
	
}
