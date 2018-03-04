import java.awt.Component;
import java.awt.Container;
import java.util.List;

public interface CalendarView {
	
	public abstract void refreshCalendar();

	public abstract void initialize();
	
	public abstract void refreshView();
	
	public abstract void attachController(CalendarControl cc);

    public abstract void updateEventAdder();
    
    public abstract void updateDayView(List<Occasion>occasions);
    
    public abstract void updateAgendaView();
    
    public abstract void updateDateTitle(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay);
 	
}
