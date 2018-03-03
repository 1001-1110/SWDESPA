import java.util.ArrayList;

public class EventProgramParser implements EventParser{

	CalendarControl cc;
	
	public EventProgramParser(CalendarControl cc) {
		this.cc = cc;
	}
	
	public void addEvent(String info, int month, int day, int year, String color) {
		cc.addEvent(new Event(info,month,day,year,color));
	}

	public void addRecurEvent(String info, int month, int day, int year, String color) {
		cc.addRecurEvent(new Event(info,month,day,year,color));
	}
	
}
