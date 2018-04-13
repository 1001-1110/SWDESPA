<<<<<<< HEAD:UltimateDesignChallenge/src/View/CalendarView.java
package View;
import java.util.ArrayList;

import Controller.CalendarControl;

public interface CalendarView {
	
	public abstract void refreshCalendar(int monthToday, int yearToday, ArrayList<Integer> days);

	public abstract void initialize();
	
	public abstract void refreshView();
	
	public abstract void attachController(CalendarControl cc);

	public abstract void attachAgendaView(AgendaView av);
	
	public abstract void attachDayView(DayView dv);	
	
	public abstract void attachWeekView(WeekView dv);	
	
	public abstract void update();
	
    public abstract void updateDateTitle(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay);
    
    public abstract void updateTime(String time);
    
    public abstract void updateNotification(boolean success, String type);
    
    public abstract void enableSelectButtons();
    
}
=======
package View;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import Controller.CalendarControl;

public interface CalendarView {
	
	public abstract void refreshCalendar(int monthToday, int yearToday, ArrayList<Integer> days);

	public abstract void initialize();
	
	public abstract void refreshView();
	
	public abstract void attachController(CalendarControl cc);

	public abstract void attachAgendaView(AgendaView av);
	
	public abstract void attachDayView(DayView dv);	
	
	public abstract void attachWeekView(WeekView dv);	
	
	public abstract void update();
	
    public abstract void updateDateTitle(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay);
    
    public abstract void updateNotification(boolean success, String type);
    
    public abstract void enableSelectButtons();
    
}
>>>>>>> 85e49b341343ded5ad428f55d4bc4ffcc5b50edc:DesignChallenge1/src/View/CalendarView.java
