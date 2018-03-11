import java.util.List;

public interface CalendarModel {

	public abstract void attachView(CalendarView cv);

	public abstract void writeDatabase(Occasion occ);
	
	public abstract void connectDatabase(String DRIVER_NAME, String URL, String USERNAME, String PASSWORD, String DATABASE);

	public abstract void updateDatabase(int occasionID, boolean isDone);

	public abstract void deleteDatabase(int occasionID);	
	
	public abstract void notifyCalendar(int monthToday, int yearToday, String dateFilter);
	
	public abstract void notifyDateTitle(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay);
	
	public abstract void notifyObservers(String dateFilter);
	
	public abstract void notifyObservers(String dateFilter, String typeFilter);
	
	public abstract void notifyDeselect();
	
	public abstract void attachObserver(ObserverView obs);
	
	public abstract void timeCheck();
	
}
