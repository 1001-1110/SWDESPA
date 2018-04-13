package Controller;
import Entry.Entries;
import Entry.EntriesCollection;
import Entry.EntriesIterator;
import Entry.Entry;
import java.util.Calendar;


public abstract class EntryFactory {
	
	public Entries makeEntries(Entries entries){
		Entries newEntries = new EntriesCollection();
		EntriesIterator ei = entries.getIterator();
		while(ei.hasNext()) {
			newEntries.add(makeEntry(ei.peek().getDateFrom(),ei.peek().getId()));
			ei.next();
		}
		return newEntries;
	}
	
	public Entries makeEntries(Calendar dateFrom, Calendar dateTo){
		Entries entries = new EntriesCollection();
		while(dateTo.after(dateFrom)) {
			Entry entry = makeEntry(dateFrom,0);
			entries.add(makeEntry(dateFrom,0));
			dateFrom.add(Calendar.MINUTE, 30);
		}
		return entries;
	}
	
	protected abstract Entry makeEntry(Calendar dateFrom, int id);
	
}
