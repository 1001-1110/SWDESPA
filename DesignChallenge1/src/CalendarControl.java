import java.util.ArrayList;

public interface CalendarControl {
		
	public abstract void refreshCalendar();
	
	public abstract void loadEvent(Event event);
	
	public abstract void loadRecurEvent(Event event);
	
	public abstract void addEvent(Event event);
	
	public abstract void addRecurEvent(Event event);
	
	public abstract ArrayList<Event> checkDateEvent(int month, int day, int year);

	public abstract void attachParser(EventParser ep);	
	
	public abstract void attachModel(CalendarModel cm);
	
	public abstract void attachView(CalendarView cv);
	
}
