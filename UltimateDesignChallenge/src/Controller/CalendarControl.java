<<<<<<< HEAD:UltimateDesignChallenge/src/Controller/CalendarControl.java
package Controller;

import Model.CalendarModel;

public interface CalendarControl {
	
	public abstract void attachModel(CalendarModel cm);
	
	public abstract boolean addSlots(String doctor, String dateFrom, String dateTo, String timeFrom, String timeTo, String slotType);
	
	public abstract boolean addBooks(String client, String bookType, int id[]);

    public abstract void updateDateTitle(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay);
    
    public abstract void updateViews(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay, boolean isEvent, boolean isTask, boolean viewDay, boolean viewWeek);
    
    public abstract void refreshCalendar(int monthToday, int yearToday);

	public abstract void refreshCalendarDays();
    
}
=======
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
>>>>>>> 85e49b341343ded5ad428f55d4bc4ffcc5b50edc:DesignChallenge1/src/Controller/CalendarControl.java
