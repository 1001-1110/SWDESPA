package Controller;
import Entry.Entry;
import Entry.Book;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BookFactory extends EntryFactory{
	
	private String client;
	private String bookType;
	
	public BookFactory(String client, String bookType) {
		this.client = client;
		this.bookType = bookType;
	}
	
	public void setClient(String client) {
		this.client = client;
	}
	
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	protected Entry makeEntry(Calendar dateFrom, int id) {
		Calendar dateTo = new GregorianCalendar();
		dateTo.setTime(dateFrom.getTime());
		dateTo.add(Calendar.MINUTE, 30);
		//YYYY-MM-DD HH:MM:SS
		//String client, String bookType, int slotsID, Calendar dateFrom, Calendar dateTo, String durationFrom, String durationTo, int id
		String durationFrom = dateFrom.get(Calendar.YEAR)+"-"+(dateFrom.get(Calendar.MONTH)+1)+"-"+dateFrom.get(Calendar.DATE)+" "+dateFrom.get(Calendar.HOUR_OF_DAY)+":"+dateFrom.get(Calendar.MINUTE)+":00";
		String durationTo = dateTo.get(Calendar.YEAR)+"-"+(dateTo.get(Calendar.MONTH)+1)+"-"+dateTo.get(Calendar.DATE)+" "+dateTo.get(Calendar.HOUR_OF_DAY)+":"+dateTo.get(Calendar.MINUTE)+":00";
		return new Book(client, bookType, id, dateFrom, dateTo,durationFrom,durationTo,0);
	}
	
}
