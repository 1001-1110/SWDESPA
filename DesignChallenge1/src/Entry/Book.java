package Entry;

import java.util.Calendar;

public class Book extends Entry{

	String client;
	
	public Book(String client, Calendar dateFrom, Calendar dateTo) {
		super(dateFrom, dateTo);
		this.client = client;
	}	
	
}
