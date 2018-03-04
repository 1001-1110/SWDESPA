
public class CalendarProgramControl implements CalendarControl{

	CalendarModel cm;
	
	public void addOccasion(String info, String date, String timeFrom, String timeTo, boolean isEvent, boolean isTask) {
		String[] splitDate = date.split("/");
		date = splitDate[2]+"-"+splitDate[0]+"-"+splitDate[1];
		
		if(isEvent) {
			cm.writeDatabase(new Event(info,date+" "+timeFrom,date+" "+timeTo,false));
		}else if(isTask) {
			cm.writeDatabase(new Task(info,date+" "+timeFrom+":00",date+" "+timeTo+":00",false));
		}
	}	
	
	public void attachModel(CalendarModel cm) {
		this.cm = cm;
	}

	public void updateEventAdder() {
		cm.notifyEventAdder();
	}

	public void updateDayView() {
		cm.notifyDayView();
	}

	public void updateAgendaView() {
		cm.notifyAgendaView();
	}

	public void updateDateTitle(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay) {
		cm.notifyDateTitle(currentSelectedYear, currentSelectedMonth, currentSelectedDay);
	}

	public void refreshCalendar(int monthToday, int yearToday) {
		cm.notifyCalendar(monthToday, yearToday);
	}
	
}
