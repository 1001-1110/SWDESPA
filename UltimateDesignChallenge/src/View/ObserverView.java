package View;

import Entry.Entries;

public interface ObserverView {

	public abstract void update(Entries slots, Entries books);
	
	public abstract void deselect();
}
