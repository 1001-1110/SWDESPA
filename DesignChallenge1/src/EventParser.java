
public interface EventParser {

	public abstract void addEvent(String info, int month, int day, int year, String color);
	
	public abstract void addRecurEvent(String info, int month, int day, int year, String color);
	
	public abstract void readCSV(String filename, boolean recur);
	
	public abstract void readPSV(String filename, boolean recur);
	
	public abstract void saveEventData(Event event);
	
	public abstract void saveRecurData(Event event);
	
	public abstract void loadData();
	
}
