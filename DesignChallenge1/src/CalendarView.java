import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

public interface CalendarView {
	
	public abstract void refreshCalendar(int monthToday, int yearToday, ArrayList<Integer> days);

	public abstract void initialize();
	
	public abstract void refreshView();
	
	public abstract void attachController(CalendarControl cc);

	public abstract void attachAgendaView(AgendaView av);
	
	public abstract void attachDayView(DayView dv);	
	
	public abstract void update();
	
    public abstract void updateDateTitle(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay);
    
    public abstract void updateNotification(boolean success, String type);
    
    public abstract void enableSelectButtons();
    
}
