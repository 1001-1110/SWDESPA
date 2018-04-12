package Controller;
import Entry.Entry;
import Entry.Slot;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SlotFactory extends EntryFactory{

	private String doctor;
	
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	
	@Override
	protected Entry makeEntry(Calendar dateFrom) {
		Calendar dateTo = new GregorianCalendar();
		dateTo.setTime(dateFrom.getTime());
		dateTo.add(Calendar.MINUTE, 30);
		return new Slot(doctor, dateFrom, dateTo);
		
	}

}
