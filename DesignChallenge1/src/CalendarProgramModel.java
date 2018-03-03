import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarProgramModel implements CalendarModel{

	GregorianCalendar current = (GregorianCalendar) Calendar.getInstance();
	ArrayList<Event> events = new ArrayList<Event>();
	ArrayList<Event> notnotified = new ArrayList<Event>();
	ArrayList<Event> notifiedrecur = new ArrayList<Event>();
	ArrayList<Event> recurevents = new ArrayList<Event>();
	CalendarControl cc;
	
	public CalendarProgramModel(CalendarControl cc) {
		this.cc = cc;
	}
	
	public boolean addEvent(Event event) {
		if(!checkOverlapEvent(event)) {
			events.add(event);	
			notnotified.add(event);
			return true;
		}
		return false;
	}
	
	public boolean addRecurEvent(Event event) {
		if(!checkOverlapRecurEvent(event)) {
			recurevents.add(event);
			return true;
		}
		return false;
	}
	
	public boolean checkOverlapRecurEvent(Event event) {
		for(int i = 0 ; i < recurevents.size(); i++) {
			if(recurevents.get(i).getInfo().equals(event.getInfo()) && recurevents.get(i).getDay() == event.getDay() && recurevents.get(i).getMonth() == event.getMonth() && recurevents.get(i).getYear() == event.getYear() && recurevents.get(i).getColorString().equals(event.getColorString())) {
				return true;
			}			
		}		
		return false;
	}
	
	public boolean checkOverlapEvent(Event event) {
		for(int i = 0 ; i < events.size(); i++) {
			if(events.get(i).getInfo().equals(event.getInfo()) && events.get(i).getDay() == event.getDay() && events.get(i).getMonth() == event.getMonth() && events.get(i).getYear() == event.getYear() && events.get(i).getColorString().equals(event.getColorString())) {
				return true;
			}			
		}		
		return false;
	}
	
	public ArrayList<Event> checkCurrentEvents() {
		current = (GregorianCalendar) Calendar.getInstance();
		ArrayList<Event> returnedevents = new ArrayList<Event>();
		for(int i = 0 ; i < notnotified.size(); i++) {
			if(notnotified.get(i).getDay() == current.get(Calendar.DATE) && notnotified.get(i).getMonth() == current.get(Calendar.MONTH)+1 && notnotified.get(i).getYear() == current.get(Calendar.YEAR)) {
				returnedevents.add(notnotified.get(i));	
				notnotified.remove(i);
			}
		}

		for(int i = 0 ; i < recurevents.size(); i++) {
			if(recurevents.get(i).getDay() == current.get(Calendar.DATE) && recurevents.get(i).getMonth() == current.get(Calendar.MONTH)+1) {
				boolean notified = false;
				for(int j = 0 ; j < notifiedrecur.size(); j++) {
					if(recurevents.get(i).getColorString().equals(notifiedrecur.get(j).getColorString()) && recurevents.get(i).getInfo().equals(notifiedrecur.get(j).getInfo()) && recurevents.get(i).getDay() == notifiedrecur.get(j).getDay() && recurevents.get(i).getMonth() == notifiedrecur.get(j).getMonth() && current.get(Calendar.YEAR) == notifiedrecur.get(j).getYear()) {
						notified = true;
					}
				}
				if(!notified) {
					returnedevents.add(new Event(recurevents.get(i).getInfo(), recurevents.get(i).getMonth(), recurevents.get(i).getDay(), (int) current.get(Calendar.YEAR), recurevents.get(i).getColorString()));	
					notifiedrecur.add(new Event(recurevents.get(i).getInfo(), recurevents.get(i).getMonth(), recurevents.get(i).getDay(), (int) current.get(Calendar.YEAR), recurevents.get(i).getColorString()));					
				}
			}
		}		
		
		return returnedevents;
	}
	
	public ArrayList<Event> checkEvents(int month, int day, int year) {
		ArrayList<Event> returnedevents = new ArrayList<Event>();
		
		for(int i = 0 ; i < events.size(); i++) {

			if(events.get(i).getDay() == day && events.get(i).getMonth() == month+1 && events.get(i).getYear() == year) {
				returnedevents.add(events.get(i));
			}
			
		}
		
		for(int i = 0 ; i < recurevents.size(); i++) {

			if(recurevents.get(i).getDay() == day && recurevents.get(i).getMonth() == month+1 && recurevents.get(i).getYear() <= year) {
				returnedevents.add(recurevents.get(i));
			}
			
		}		
		
		return returnedevents;
	}
		
	public int getCurrentYear() {
		return (int) current.get(Calendar.YEAR);
	}

}
