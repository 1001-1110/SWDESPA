import java.util.List;

public class CalendarProgramModel implements CalendarModel{

	private int currentSelectedYear, currentSelectedMonth, currentSelectedDay;
	
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
		return d.getOccasions(dateFilter);
	}

	private List<Occasion> readDatabase(String dateFilter, String typeFilter) {
		return d.getOccasions(dateFilter, typeFilter);
	}	
	
	public void updateDatabase(String dateFrom) {
		d.updateIsDone(dateFrom);
	}

	public void deleteDatabase(String dateFrom) {
		d.deleteOccasion(dateFrom);
	}	
	
	public void attachView(CalendarView cv) {
		this.cv = cv;
	}

	public void notifyViews(String dateFilter) {
		cv.updateViews(readDatabase(dateFilter));
	}

	public void notifyFilteredViews(String dateFilter, String typeFilter) {
		cv.updateViews(readDatabase(dateFilter,typeFilter));
	}
	
	public void notifyDateTitle(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay) {
		this.currentSelectedYear = currentSelectedYear;
		this.currentSelectedMonth = currentSelectedMonth;
		this.currentSelectedDay = currentSelectedDay;
		cv.updateDateTitle(currentSelectedYear, currentSelectedMonth, currentSelectedDay);
	}
	
	public void notifyCalendar(int monthToday, int yearToday) {

	}

}
