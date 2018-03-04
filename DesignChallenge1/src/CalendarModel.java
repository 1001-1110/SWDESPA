import java.util.List;

public interface CalendarModel {

	public abstract void attachView(CalendarView cv);
	
	public abstract List<Occasion> readDatabase();

	public abstract void writeDatabase(Occasion occ);
	
	public abstract void connectDatabase(String DRIVER_NAME, String URL, String USERNAME, String PASSWORD, String DATABASE);

	public abstract void notifyEventAdder();

	public abstract void notifyDayView();

	public abstract void notifyAgendaView();
	
	public abstract void notifyDateTitle(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay);
	
	public abstract void notifyCalendar(int monthToday, int yearToday);
}
