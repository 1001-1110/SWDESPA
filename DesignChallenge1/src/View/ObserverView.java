package View;
import java.util.List;

import Entry.Occasion;

public interface ObserverView {

	public abstract void update(List<Occasion>occasions);
	
	public abstract void deselect();
}
