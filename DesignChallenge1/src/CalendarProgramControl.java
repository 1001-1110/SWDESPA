import java.util.ArrayList;

public class CalendarProgramControl implements CalendarControl{

	CalendarModel cm;
	CalendarView cv;
	EventParser ep;
	ArrayList<Observer> obs = new ArrayList<Observer>();
	
	public void startInstructions() {
        
		ep.loadData();
		refreshCalendar();
		
        Thread instructions = new Thread() {
        	public void run() {
        		while(true) {
        			
            		try {
                		Thread.sleep(1000);           			
            		}catch(InterruptedException e) {} 
            		
            		ArrayList<Event> currentEvents = cm.checkCurrentEvents();
            		
        			if(currentEvents.size() > 0)
        				for(int i = 0 ; i < currentEvents.size() ; i++)
        					update(currentEvents.get(i));
        			
        		}
        	}
        };
        
        instructions.start();
        
	}

	public void loadEvent(Event event) {
		cm.addRecurEvent(event);
	}
	
	public void loadRecurEvent(Event event) {
		cm.addEvent(event);
	}
	
	public void addEvent(Event event) {
		if(cm.addEvent(event))
			ep.saveEventData(event);
		refreshCalendar();
	}	
	
	public void addRecurEvent(Event event) {
		if(cm.addRecurEvent(event))
			ep.saveRecurData(event);
		refreshCalendar();
	}
	
	public ArrayList<Event> checkDateEvent(int month, int day, int year) {
		return cm.checkEvents(month, day, year);
	}
	
	public void refreshCalendar() {
		cv.refreshCalendar();
	}

	public void attachParser(EventParser ep) {
		this.ep = ep;
	}
	
	public void attachModel(CalendarModel cm) {
		this.cm = cm;
	}
	
	public void attachView(CalendarView cv) {
		this.cv = cv;
	}
	
	public void attachObserver(Observer ob) {
		obs.add(ob);
	}
	
	public void update(Event event) {
		for(int i = 0 ; i < obs.size() ; i++)
			obs.get(i).update(event);
	}
	
}
