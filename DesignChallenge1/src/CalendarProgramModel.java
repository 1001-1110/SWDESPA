import java.util.ArrayList;
import java.util.List;

public class CalendarProgramModel implements CalendarModel{

	String dateFilter, typeFilter;
	boolean isFiltered;
	
	CalendarView cv;
	DatabaseWriter dbw;
	DatabaseReader dbr;
	DatabaseConnector dc;
	Database d;
	
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
	
	public void updateDatabase(String dateFrom, boolean isDone) {
		d.updateIsDone(dateFrom, isDone);
	}	
	
	public void deleteDatabase(String dateFrom) {
		d.deleteOccasion(dateFrom);
	}	
	
	public void attachView(CalendarView cv) {
		this.cv = cv;
	}

	public void notifyViews(String dateFilter) {
		this.dateFilter = dateFilter;
		cv.updateViews(readDatabase(dateFilter));
	}

	public void notifyFilteredViews(String dateFilter, String typeFilter) {
		this.dateFilter = dateFilter;
		this.typeFilter = typeFilter;
		cv.updateViews(readDatabase(dateFilter,typeFilter));
	}
	
	public void notifyDateTitle(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay) {
		cv.updateDateTitle(currentSelectedYear, currentSelectedMonth, currentSelectedDay);
	}
	
	public void notifyCalendar(int monthToday, int yearToday, String dateFilter) {
		List<Occasion>occasions = readDatabase(dateFilter);
		List<Integer>days = new ArrayList<>();
		for(int i = 0 ; i < occasions.size() ; i++) {
			if(occasions.get(i) instanceof Event)
				days.add(Integer.parseInt(((Event) occasions.get(i)).getDurationFrom().substring(8,10)));
			else if(occasions.get(i) instanceof Task)
				days.add(Integer.parseInt(((Task) occasions.get(i)).getDurationFrom().substring(8,10)));
		}
		cv.refreshCalendar(monthToday, yearToday, (ArrayList<Integer>) days);
	}
	
	public void timeCheck() {
		/*Thread tc = new Thread(){
	        public void run(){  
	        	while(true) {
	        		if(isFiltered)
	        			notifyFilteredViews(dateFilter, typeFilter);
	        		else
	        			notifyViews(dateFilter);
		        	try {
						sleep(1000);
					} catch (InterruptedException e) {}
	        	}

	        }			
		};
		tc.start();*/
	}

}
