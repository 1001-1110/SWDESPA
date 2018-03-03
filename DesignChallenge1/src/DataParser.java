import java.util.ArrayList;

public abstract class DataParser {

	ArrayList<Event> events = new ArrayList<Event>();
	
	public ArrayList<Event> readFile(String filename){
		readData(filename);
		return events;
	}
	
	abstract void readData(String filename);

	abstract void writeData(String filename, Event event);
	
	public void processData(String info, int month, int day, int year, String color) {
		events.add(new Event(info,month,day,year,color));
	}

}
