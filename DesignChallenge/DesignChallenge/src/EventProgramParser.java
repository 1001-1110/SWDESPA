import java.util.ArrayList;

public class EventProgramParser implements EventParser{

	CalendarControl cc;
	DataParser csv = new CSVParser();
	DataParser psv = new PSVParser();
	
	public EventProgramParser(CalendarControl cc) {
		this.cc = cc;
	}
	
	public void readCSV(String filename, boolean recur) {
		ArrayList<Event> events = csv.readFile(filename);
		for(int i = 0 ; i < events.size() ; i++)
			if(recur)
				cc.addRecurEvent(events.get(i));
			else
				cc.addEvent(events.get(i));
	}

	public void readPSV(String filename, boolean recur) {
		ArrayList<Event> events = psv.readFile(filename);
		for(int i = 0 ; i < events.size() ; i++)
			if(recur)
				cc.addRecurEvent(events.get(i));
			else
				cc.addEvent(events.get(i));
	}	
	
	public void saveEventData(Event event){
		csv.writeData("EventData.csv",event);
	}
	
	public void saveRecurData(Event event) {
		csv.writeData("RecurData.csv",event);
	}
	
	public void loadData() {
		ArrayList<Event> events = csv.readFile("EventData.csv");
		ArrayList<Event> recurevents = csv.readFile("RecurData.csv");		
		for(int i = 0 ; i < events.size() ; i++)
				cc.loadEvent(events.get(i));
		for(int i = 0 ; i < recurevents.size() ; i++)
				cc.loadRecurEvent(recurevents.get(i));
	}
	
	public void addEvent(String info, int month, int day, int year, String color) {
		cc.addEvent(new Event(info,month,day,year,color));
	}

	public void addRecurEvent(String info, int month, int day, int year, String color) {
		cc.addRecurEvent(new Event(info,month,day,year,color));
	}
	
}
