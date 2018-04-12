package Entry;

import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Entry {
	
	int id;
	Calendar dateFrom;
	Calendar dateTo;
	
	public int getId() {
		return id;
	}

	public Calendar getDateFrom() {
		return dateFrom;
	}

	public Calendar getDateTo() {
		return dateTo;
	}
	
	public Entry(Calendar dateFrom, Calendar dateTo) {
		this.id = 0;
		this.dateFrom = new GregorianCalendar();
		this.dateTo = new GregorianCalendar();
		this.dateFrom.setTime(dateFrom.getTime());
		this.dateTo.setTime(dateTo.getTime());
	}
	
}
