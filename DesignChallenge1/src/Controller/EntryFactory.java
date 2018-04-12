package Controller;
import Entry.Entry;

import java.util.ArrayList;
import java.util.Calendar;


public abstract class EntryFactory {
	
	public ArrayList<Entry> makeEntries(Calendar dateFrom, Calendar dateTo){
		ArrayList<Entry> entries = new ArrayList<>();
		while(dateTo.after(dateFrom)) {
			entries.add(makeEntry(dateFrom));
			dateFrom.add(Calendar.MINUTE, 30);
		}
		return entries;
	}
	
	protected abstract Entry makeEntry(Calendar dateFrom);
	
}
