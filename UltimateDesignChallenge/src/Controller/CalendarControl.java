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

