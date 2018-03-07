import java.util.List;

public interface CalendarModel {

	public abstract void attachView(CalendarView cv);

	public abstract void writeDatabase(Occasion occ);
	
	public abstract void connectDatabase(String DRIVER_NAME, String URL, String USERNAME, String PASSWORD, String DATABASE);

	public abstract void updateDatabase(String dateFrom);

	public abstract void deleteDatabase(String dateFrom);	
	
	public abstract void notifyViews(String dateFilter);
	
	public abstract void notifyDateTitle(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay);
	
	public abstract void notifyCalendar(int monthToday, int yearToday);
	
	public abstract void notifyFilteredViews(String dateFilter, String typeFilter);
	
}
