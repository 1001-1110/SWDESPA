import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarProgramModel implements CalendarModel{

	CalendarView cv;
	DatabaseWriter dbw;
	DatabaseReader dbr;
	
	public void writeDatabase(Occasion occ) {
		
	}
	
	public Occasion readDatabase() {
		return null;
	}
	
	public void attachView(CalendarView cv) {
		this.cv = cv;
	}
	

}
