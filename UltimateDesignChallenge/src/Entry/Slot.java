package Entry;

import java.util.Calendar;

public class Slot extends Entry{

	String doctor;
	String slotType;
	
	public String getDoctor() {
		return doctor;
	}

	public Slot(String doctor, String slotType, Calendar dateFrom, Calendar dateTo, String durationFrom, String durationTo, int id) {
		super(dateFrom, dateTo, durationFrom, durationTo, id);
		this.slotType = slotType;
		this.doctor = doctor;
	}
	
}
