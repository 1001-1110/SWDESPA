package Entry;

import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Entry {
	
	private int id;
	private Calendar dateFrom;
	private Calendar dateTo;
	private String durationFrom;
	private String durationTo;
	
	public int getId() {
		return id;
	}

	public Calendar getDateFrom() {
		return dateFrom;
	}

	public Calendar getDateTo() {
		return dateTo;
	}
	
	public String getDurationFrom() {
		return durationFrom;
	}

	public String getDurationTo() {
		return durationTo;
	}

	public Entry(Calendar dateFrom, Calendar dateTo, String durationFrom, String durationTo, int id) {
		this.id = id;
		this.durationFrom = durationFrom;
		this.durationTo = durationTo;
		this.dateFrom = new GregorianCalendar();
		this.dateTo = new GregorianCalendar();
		this.dateFrom.setTime(dateFrom.getTime());
		this.dateTo.setTime(dateTo.getTime());
	}
	
}
