import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarProgramModel implements CalendarModel{

	CalendarView cv;

	@Override
	public void attachView(CalendarView cv) {
		this.cv = cv;
	}
	

}
