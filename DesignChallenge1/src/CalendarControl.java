import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

public interface CalendarControl {
	
	public abstract void attachModel(CalendarModel cm);
	
	public abstract void addOccasion(String info, String date, String timeFrom, String timeTo, boolean isEvent, boolean isTask);

    public abstract void updateDateTitle(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay);
    
    public abstract void updateViews(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay, boolean isEvent, boolean isTask);
    
    public abstract void updateIsDone(String dateFrom);
    
    public abstract void deleteIsDone(String dateFrom);
    
	public abstract void refreshCalendar(int monthToday, int yearToday);
    
}
