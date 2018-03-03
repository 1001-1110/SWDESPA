
public interface EventParser {

	public abstract void addEvent(String info, int month, int day, int year, String color);
	
	public abstract void addRecurEvent(String info, int month, int day, int year, String color);
	
}
