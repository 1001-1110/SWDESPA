import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CalendarProgramModel implements CalendarModel{

	String dateFilter, typeFilter, monthFilter;
	boolean isFiltered;
	int monthToday, yearToday;
	
	CalendarView cv;
	DatabaseWriter dbw;
	DatabaseReader dbr;
	DatabaseConnector dc;
	Database d;
	
	ArrayList<ObserverView> observers;
	
	public CalendarProgramModel() {
		observers = new ArrayList<>();
	}
	
	public void connectDatabase(String DRIVER_NAME, String URL, String USERNAME, String PASSWORD, String DATABASE) {
		dc = new DatabaseProgramConnector(DRIVER_NAME,URL,USERNAME,PASSWORD,DATABASE);
		d = new DatabaseProgram(dc);
	}
	
	public void writeDatabase(Occasion occ) {
		if(d.addOccasion(occ)) {
			if(occ instanceof Event)
				cv.updateNotification(true,"Event");
			else if(occ instanceof Task)
				cv.updateNotification(true,"Task");			
		}else {
			if(occ instanceof Event)
				cv.updateNotification(false,"Event");
			else if(occ instanceof Task)
				cv.updateNotification(false,"Task");				
		}	
	}
	
	private List<Occasion> readDatabase(String dateFilter) {
		isFiltered = false;
		return d.getOccasions(dateFilter);
	}

	private List<Occasion> readDatabase(String dateFilter, String typeFilter) {
		isFiltered = true;
		return d.getOccasions(dateFilter, typeFilter);
	}	

	private boolean readMonthDatabase(String monthFilter) {
		return d.readOccasion(monthFilter);
	}		
	
	public void updateDatabase(int occasionID, boolean isDone) {
		d.updateIsDone(occasionID, isDone);
	}	

	private boolean updateEventDatabase(String currentDateTime) {
		return d.updateEventIsDone(currentDateTime);
	}		
	
	public void deleteDatabase(int occasionID) {
		d.deleteOccasion(occasionID);
	}	
	
	public void attachView(CalendarView cv) {
		this.cv = cv;
	}
	
	public void attachObserver(ObserverView ov) {
		observers.add(ov);
	}

	public void notifyObservers(String dateFilter) {
		this.dateFilter = dateFilter;
		for(int i = 0 ; i < observers.size() ; i++)
			observers.get(i).update(readDatabase(dateFilter));
	}

	public void notifyObservers(String dateFilter, String typeFilter) {
		this.dateFilter = dateFilter;
		this.typeFilter = typeFilter;
		for(int i = 0 ; i < observers.size() ; i++)
			observers.get(i).update(readDatabase(dateFilter,typeFilter));
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
		List<Integer>days = new ArrayList<>();
		for(int i = 0 ; i <= 31 ; i++) {
			String day;
			if(i < 10)
				day = "0" + i;
			else
				day = Integer.toString(i);
			if(readMonthDatabase(monthFilter+"-"+day)) {
				days.add(i);				
			}
		}
		cv.refreshCalendar(monthToday, yearToday, (ArrayList<Integer>) days);
	}
	
	public void timeCheck() {
		Thread tc = new Thread(){
	        public void run(){  
	        	while(true) {
	        		GregorianCalendar cal = new GregorianCalendar();
	        		String currentDateTime = cal.get(Calendar.YEAR) +"-"+ (cal.get(Calendar.MONTH)+1) +"-"+ cal.get(Calendar.DATE)
	        		+" "+ cal.get(Calendar.HOUR_OF_DAY);
	        		
	        		if(cal.get(Calendar.MINUTE) < 10)
	        			currentDateTime += ":0"+cal.get(Calendar.MINUTE)+":00";
	        		else
	        			currentDateTime += ":"+cal.get(Calendar.MINUTE)+":00";

	        		if(updateEventDatabase(currentDateTime)) {
		        		if(isFiltered)
		        			notifyObservers(dateFilter, typeFilter);
		        		else
		        			notifyObservers(dateFilter);	        			
	        		}
        		
	        		notifyCalendar(monthToday,yearToday,monthFilter);
		        	try {
						sleep(1000);
					} catch (InterruptedException e) {}
	        	}

	        }			
		};
		tc.start();
	}

}
