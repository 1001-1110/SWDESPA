import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

public interface CalendarControl {
	
	public abstract void attachModel(CalendarModel cm);
	
	public abstract void addOccasion(String info, String date, String timeFrom, String timeTo, boolean isEvent, boolean isTask);

    public abstract void updateEventAdder();
    
    public abstract void updateDayView(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay);
    
    public abstract void updateAgendaView(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay);
    
    public abstract void updateDateTitle(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay);
 
	public abstract void refreshCalendar(int monthToday, int yearToday);
    
}
