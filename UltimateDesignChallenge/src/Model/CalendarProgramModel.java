package Model;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import Database.Database;
import Entry.Entries;
import Entry.EntriesIterator;
import View.CalendarView;
import View.ObserverView;

public class CalendarProgramModel implements CalendarModel{

	String firstFilter, secondFilter, personFilter, monthFilter;
	boolean isFiltered, weekView;
	int monthToday, yearToday;
	List<Integer>days;
	
	CalendarView cv;
	
	ArrayList<ObserverView> observers;
	
	public CalendarProgramModel() {
		observers = new ArrayList<>();
	}
	
	public void writeDatabase(Entries entries) {
		if(Database.getInstance().addEntries(entries)) {
			
		}else {
			
		}
	}
	
	private Entries readSlotDatabase(String dateFilter) {
		isFiltered = false;
		return Database.getInstance().getSlots(dateFilter);
	}

	private Entries readSlotDatabase(String dateFilter, String personFilter) {
		isFiltered = true;
		return Database.getInstance().getSlots(dateFilter, personFilter);
	}	

	private Entries readBookDatabase(String dateFilter) {
		isFiltered = false;
		return Database.getInstance().getBooks(dateFilter);
	}

	private Entries readBookDatabase(String dateFilter, String personFilter) {
		isFiltered = true;
		return Database.getInstance().getBooks(dateFilter, personFilter);
	}		
	
	private Entries readMonthSlotDatabase(String monthFilter) {
		return Database.getInstance().getMonthSlots(monthFilter);
	}		

	private Entries readMonthBookDatabase(String monthFilter) {
		return Database.getInstance().getMonthBooks(monthFilter);
	}		
	
	private Entries readWeekSlotDatabase(String firstFilter, String secondFilter){
		isFiltered = false;
		return Database.getInstance().getWeekSlots(firstFilter, secondFilter);
	}

	private Entries readWeekBookDatabase(String firstFilter, String secondFilter){
		isFiltered = false;
		return Database.getInstance().getWeekBooks(firstFilter, secondFilter);
	}	
	
	private Entries readWeekSlotDatabase(String firstFilter, String secondFilter, String personFilter){
		isFiltered = true;
		return Database.getInstance().getWeekSlots(firstFilter, secondFilter, personFilter);
	}	
	
	private Entries readWeekBookDatabase(String firstFilter, String secondFilter, String personFilter){
		isFiltered = true;
		return Database.getInstance().getWeekBooks(firstFilter, secondFilter, personFilter);
	}	
	
	public Entries readIDSlotDatabase(int id[]) {
		return Database.getInstance().getIDSlots(id);
	}
	
	public Entries readIDBookDatabase(int id[]) {
		return Database.getInstance().getIDBooks(id);
	}
	
	public void attachView(CalendarView cv) {
		this.cv = cv;
	}
	
	public void attachObserver(ObserverView ov) {
		observers.add(ov);
	}

	public void notifyObservers(String firstFilter, String secondFilter, boolean weekView) {
		this.firstFilter = firstFilter;
		this.secondFilter = secondFilter;
		this.weekView = weekView;
		if(weekView) {
			for(int i = 0 ; i < observers.size() ; i++)
				observers.get(i).update(readWeekSlotDatabase(firstFilter, secondFilter),readWeekBookDatabase(firstFilter, secondFilter));				
		}else {
			for(int i = 0 ; i < observers.size() ; i++)
				observers.get(i).update(readSlotDatabase(firstFilter),readBookDatabase(firstFilter));			
		}
	}	
	
	public void notifyObservers(String firstFilter, String secondFilter, String personFilter, boolean weekView) {
		this.firstFilter = firstFilter;
		this.secondFilter = secondFilter;
		this.personFilter = personFilter;
		this.weekView = weekView;
		if(weekView) {
			for(int i = 0 ; i < observers.size() ; i++)
				observers.get(i).update(readWeekSlotDatabase(firstFilter,secondFilter,personFilter),readWeekBookDatabase(firstFilter,secondFilter,personFilter));		
		}else {
			for(int i = 0 ; i < observers.size() ; i++)
				observers.get(i).update(readSlotDatabase(firstFilter,personFilter),readBookDatabase(firstFilter,personFilter));			
		}

	}
	
	public void notifyDeselect() {
		for(int i = 0 ; i < observers.size() ; i++)
			observers.get(i).deselect();		
		cv.update();
	}
	
	public void notifyDateTitle(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay) {
		cv.updateDateTitle(currentSelectedYear, currentSelectedMonth, currentSelectedDay);
	}
	
	public void notifyCalendar(int monthToday, int yearToday, String monthFilter) {
		this.monthToday = monthToday;
		this.yearToday = yearToday;
		this.monthFilter = monthFilter;
		if(days == null)
			refreshDays();
		cv.refreshCalendar(monthToday, yearToday, (ArrayList<Integer>) days);
	}
	
	public void refreshDays() {
		Entries entries = readMonthSlotDatabase(monthFilter);
		EntriesIterator ei = entries.getIterator();
		days = new ArrayList<>();
		while(ei.hasNext()) {
				String splitFrom[] = ei.next().getDurationFrom().split(" ");
				String dateSplitFrom[] = splitFrom[0].split("-");
				days.add(Integer.parseInt(dateSplitFrom[2]));
			}
	
	}
	
	public void timeCheck() {
		Thread tc = new Thread(){
	        public void run(){  
	        	while(true) {
	        		GregorianCalendar cal = new GregorianCalendar();
	        		String currentTime = Integer.toString(cal.get(Calendar.HOUR_OF_DAY));       		
	        		
	        		if(cal.get(Calendar.MINUTE) < 10)
	        			currentTime += ":0"+cal.get(Calendar.MINUTE);
	        		else
	        			currentTime += ":"+cal.get(Calendar.MINUTE);
	        		
	        		if(cal.get(Calendar.SECOND) < 10)
	        			currentTime += ":0"+cal.get(Calendar.SECOND);
	        		else
	        			currentTime += ":"+cal.get(Calendar.SECOND);

	        		cv.updateTime(currentTime);
	        		/*if(updateEventDatabase(currentDateTime)) {
			        	if(isFiltered)
			        		notifyObservers(firstFilter, secondFilter, personFilter, weekView);
			        	else
			        		notifyObservers(firstFilter, secondFilter, weekView);
	        		}*/
	        		
		        	if(isFiltered)
		        		notifyObservers(firstFilter, secondFilter, personFilter, weekView);
		        	else
		        		notifyObservers(firstFilter, secondFilter, weekView);
		        	
	        		refreshDays();
	        		notifyCalendar(monthToday, yearToday, monthFilter);
		        	try {
						sleep(900);
					} catch (InterruptedException e) {}
	        	}

	        }			
		};
		tc.start();
	}
	
}
