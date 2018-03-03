import java.util.ArrayList;

public interface CalendarModel {

	public abstract boolean addEvent(Event event);
	public abstract boolean addRecurEvent(Event event);
	public abstract ArrayList<Event> checkCurrentEvents();
	public abstract ArrayList<Event> checkEvents(int month, int day, int year);
	
}
