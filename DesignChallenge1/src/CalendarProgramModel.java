import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CalendarProgramModel implements CalendarModel{

	String firstFilter, secondFilter, typeFilter, monthFilter;
	boolean isFiltered, viewType;
	int monthToday, yearToday;
	List<Integer>days;
	
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

	private List<Occasion> readMonthDatabase(String monthFilter) {
		return d.getMonthOccasion(monthFilter);
	}		
	
	private List<Occasion> readWeekDatabase(String firstFilter, String secondFilter){
		isFiltered = false;
		return d.getWeekOccasion(firstFilter, secondFilter);
	}

	private List<Occasion> readWeekDatabase(String firstFilter, String secondFilter, String typeFilter){
		isFiltered = true;
		return d.getWeekOccasion(firstFilter, secondFilter, typeFilter);
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

	public void notifyObservers(String firstFilter, String secondFilter, boolean viewType) {
		this.firstFilter = firstFilter;
		this.secondFilter = secondFilter;
		this.viewType = viewType;
		if(viewType) {
			for(int i = 0 ; i < observers.size() ; i++)
				observers.get(i).update(readWeekDatabase(firstFilter, secondFilter));				
		}else {
			for(int i = 0 ; i < observers.size() ; i++)
				observers.get(i).update(readDatabase(firstFilter));			
		}
	}	
	
	public void notifyObservers(String firstFilter, String secondFilter, String typeFilter, boolean viewType) {
		this.firstFilter = firstFilter;
		this.secondFilter = secondFilter;
		this.typeFilter = typeFilter;
		this.viewType = viewType;
		if(viewType) {
			for(int i = 0 ; i < observers.size() ; i++)
				observers.get(i).update(readWeekDatabase(firstFilter,secondFilter,typeFilter));		
		}else {
			for(int i = 0 ; i < observers.size() ; i++)
				observers.get(i).update(readDatabase(firstFilter,typeFilter));			
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
		List<Occasion>occasions = readMonthDatabase(monthFilter);
		days = new ArrayList<>();
		for(int i = 0 ; i < occasions.size(); i ++) {
			if(occasions.get(i) instanceof Event) {
				String splitFrom[] = ((Event) occasions.get(i)).getDurationFrom().split(" ");
				String splitTo[] = ((Event) occasions.get(i)).getDurationTo().split(" ");
				String dateSplitFrom[] = splitFrom[0].split("-");
				String dateSplitTo[] = splitTo[0].split("-");
				if(splitFrom[0].equals(splitTo[0])) {
					days.add(Integer.parseInt(dateSplitFrom[2]));
				}else {
					Calendar c1 = new GregorianCalendar(Integer.parseInt(dateSplitFrom[0]),Integer.parseInt(dateSplitFrom[1]),Integer.parseInt(dateSplitFrom[2]));
					Calendar c2 = new GregorianCalendar(Integer.parseInt(dateSplitTo[0]),Integer.parseInt(dateSplitTo[1]),Integer.parseInt(dateSplitTo[2]));
					while(c2.compareTo(c1) >= 0) {
						days.add(c2.get(Calendar.DATE));
						c2.add(Calendar.DATE, -1);
					}
				}
					
			}else if(occasions.get(i) instanceof Task) {
				String splitFrom[] = ((Task) occasions.get(i)).getDurationFrom().split(" ");
				String dateSplitFrom[] = splitFrom[0].split("-");
				days.add(Integer.parseInt(dateSplitFrom[2]));
			}
			
		}

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
			        		notifyObservers(firstFilter, secondFilter, typeFilter, viewType);
			        	else
			        		notifyObservers(firstFilter, secondFilter, viewType);
	        		}
        		
	        		refreshDays();
	        		notifyCalendar(monthToday, yearToday, monthFilter);
		        	try {
						sleep(2000);
					} catch (InterruptedException e) {}
	        	}

	        }			
		};
		tc.start();
	}

}
