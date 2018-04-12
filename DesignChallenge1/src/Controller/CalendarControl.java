package Controller;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import Model.CalendarModel;

public interface CalendarControl {
	
	public abstract void attachModel(CalendarModel cm);
	
	public abstract boolean addOccasion(String info, String dateFrom, String dateTo, String timeFrom, String timeTo, boolean isEvent, boolean isTask);

    public abstract void updateDateTitle(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay);
    
    public abstract void updateViews(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay, boolean isEvent, boolean isTask, boolean viewDay, boolean viewWeek);
    
    public abstract void updateIsDone(int occasionID, boolean isDone);
    
    public abstract void deleteIsDone(int occasionID);
    
    public abstract void refreshCalendar(int monthToday, int yearToday);
    
    public abstract void refreshCalendarDays();
    
}