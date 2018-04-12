package Entry;

import java.util.Calendar;

public class Slot extends Entry{

	String doctor;
	
	public Slot(String doctor, Calendar dateFrom, Calendar dateTo) {
		super(dateFrom, dateTo);
		this.doctor = doctor;
	}
	
}
