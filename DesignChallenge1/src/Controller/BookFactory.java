package Controller;
import Entry.Entry;
import Entry.Book;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BookFactory extends EntryFactory{
	
	private String client;
	
	public void setClient(String client) {
		this.client = client;
	}
	
	protected Entry makeEntry(Calendar dateFrom) {
		Calendar dateTo = new GregorianCalendar();
		dateTo.setTime(dateFrom.getTime());
		dateTo.add(Calendar.MINUTE, 30);
		return new Book(client, dateFrom, dateTo);
	}
	
}
