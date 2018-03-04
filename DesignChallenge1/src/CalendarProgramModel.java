import java.util.List;

public class CalendarProgramModel implements CalendarModel{

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
		d.addOccasion(occ);
	}
	
	public List<Occasion> readDatabase() {
		return d.getOccasions();
	}
	
	public void attachView(CalendarView cv) {
		this.cv = cv;
	}
	

}
