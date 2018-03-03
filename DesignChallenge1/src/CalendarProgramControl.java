import java.util.ArrayList;

public class CalendarProgramControl implements CalendarControl{

	CalendarModel cm;
	CalendarView cv;
	EventParser ep;

	public void loadEvent(Event event) {
		cm.addRecurEvent(event);
	}
	
	public void loadRecurEvent(Event event) {
		cm.addEvent(event);
	}
	
	public void addEvent(Event event) {

	}	
	
	public void addRecurEvent(Event event) {

	}
	
	public ArrayList<Event> checkDateEvent(int month, int day, int year) {
		return cm.checkEvents(month, day, year);
	}
	
	public void refreshCalendar() {
		cv.refreshCalendar();
	}

	public void attachParser(EventParser ep) {
		this.ep = ep;
	}
	
	public void attachModel(CalendarModel cm) {
		this.cm = cm;
	}
	
	public void attachView(CalendarView cv) {
		this.cv = cv;
	}
	
}
