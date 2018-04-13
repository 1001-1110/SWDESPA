package Entry;

import java.util.ArrayList;
import java.util.List;

public class EntriesCollection extends Entries{
    private List<Entry> entries = new ArrayList<>();

    public void add(Entry entry) {
        entries.add(entry);
    }
    
    public int size() {
    	return entries.size();
    }

    public EntriesIterator getIterator() {
        return new EntriesIterator(entries);
    }
}