package Entry;

public abstract class Entries {

    public abstract void add(Entry entry);
    
    public abstract int size();

    public abstract EntriesIterator getIterator();
}
