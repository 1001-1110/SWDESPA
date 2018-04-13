package Entry;

import java.util.Calendar;

public class Book extends Entry{

	String client;
	String bookType;
	int slotsID;
	
	public String getClient() {
		return client;
	}

	public int getSlotsID() {
		return slotsID;
	}

	public Book(String client, String bookType, int slotsID, Calendar dateFrom, Calendar dateTo, String durationFrom, String durationTo, int id) {
		super(dateFrom, dateTo, durationFrom, durationTo, id);
		this.bookType = bookType;
		this.slotsID = slotsID;
		this.client = client;
	}	
	
}
