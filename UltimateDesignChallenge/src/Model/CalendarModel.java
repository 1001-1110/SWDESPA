package Model;

import Entry.Entries;
import View.CalendarView;
import View.ObserverView;

public interface CalendarModel {

	public abstract void attachView(CalendarView cv);

	public abstract void writeDatabase(Entries entries);

	//public abstract void updateDatabase(int occasionID, boolean isDone);

	//public abstract void deleteDatabase(int occasionID);	
	
	public abstract Entries readIDSlotDatabase(int id[]);
	
	public abstract Entries readIDBookDatabase(int id[]);
	
	public abstract void notifyCalendar(int monthToday, int yearToday, String dateFilter);
	
	public abstract void notifyDateTitle(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay);
	
	public abstract void notifyObservers(String firstFilter, String secondFilter, boolean viewType);
	
	public abstract void notifyObservers(String firstFilter, String secondFilter, String typeFilter, boolean viewType);
	
	public abstract void notifyDeselect();
	
	public abstract void attachObserver(ObserverView obs);

	public abstract void refreshDays();

	public abstract void timeCheck();
	
}
