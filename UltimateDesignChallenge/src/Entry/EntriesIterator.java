package Entry;

import java.util.Iterator;
import java.util.List;

public class EntriesIterator implements Iterator<Entry> {
    private List<Entry> entries;
    private int pos = 0;

    public EntriesIterator(List<Entry> entries) {
        this.entries = entries;
    }

    public Entry peek() {
    	Entry entry = entries.get(pos);
    	return entry;
    }
    
    public int getPos() {
    	return pos;
    }
    
    public Entry first() {
    	return entries.get(0);
    }

    public Entry last() {
    	return entries.get(entries.size()-1);
    }    
    
	public Entry next() {
		Entry entry = entries.get(pos);
		pos += 1;
		return entry;
	}
 
	public boolean hasNext() {
		if (pos >= entries.size() || entries.get(pos) == null) {
			return false;
		} else {
			return true;
		}
	}
}